package com.eurovision.sandbox.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurovision.sandbox.repository.CitiesDBRepository;
import com.eurovision.sandbox.repository.CitiesRepository;
import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Service Class
 * 
 * @author clara.munoz
 */
@Service
public class CitiesServiceImpl implements CitiesService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CitiesServiceImpl.class);

	/** The CitiesDBRepository. */
	@Autowired
	private CitiesDBRepository citiesDBRepository;

	/** The CitiesRepository. */
	@Autowired
	private CitiesRepository citiesRepository;

	/** The WordsService. */
	@Autowired
	private WordsService wordsService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<>();
		citiesDBRepository.findAll().forEach(cities::add);
		return cities;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityResponse getCitiesByService(Integer page, Integer size) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 5;
		}
		return citiesRepository.getCitiesByService(page, size);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityResponse getCitiesByQuery(Integer page, Integer size) {
		if (page == null) {
			page = 0;
		}
		if (size == null) {
			size = 5;
		}
		Long count = getTotalCount();

		// Calculates the total pages with this size
		int totalPages = (count.intValue() / size) + 1;
		int lastId = (page + 1) * size;
		List<City> cities = getAllCities();

		// Gets the sublist that corresponds with the selected page
		List<City> subList = cities.subList(lastId - size, lastId);
		return getCityResponse(page, size, count, totalPages, subList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getBestCity(int length) {
		List<String> list = citiesDBRepository.getCitiesByLength(length);
		return wordsService.findBestCity(list);

	}

	/**
	 * Constructs a CityResponse object
	 * 
	 * @param page       the page number
	 * @param size       the size number
	 * @param count      total in page
	 * @param totalPages total pages
	 * @param subList    the content
	 * @return CityResponse
	 */
	private CityResponse getCityResponse(int page, int size, Long count, int totalPages, List<City> subList) {
		CityResponse cr = new CityResponse();
		cr.setContent(subList);
		cr.setLast(page == totalPages);
		cr.setNumber(page);
		cr.setSize(size);
		cr.setTotalElements(count.intValue());
		cr.setTotalPages((int) totalPages);
		return cr;
	}

	/**
	 * Gets the total rows
	 * 
	 * @return long
	 */
	private long getTotalCount() {
		return citiesDBRepository.count();
	}

}
