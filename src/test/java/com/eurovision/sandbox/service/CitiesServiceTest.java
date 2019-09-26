package com.eurovision.sandbox.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.eurovision.sandbox.mocks.Mocks;
import com.eurovision.sandbox.repository.CitiesDBRepository;
import com.eurovision.sandbox.repository.CitiesRepository;
import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Service Test
 * 
 * @author clara.munoz
 */
public class CitiesServiceTest {

	/** The CitiesDBRepository. */
	@Mock
	private CitiesDBRepository citiesDBRepository;

	/** The CitiesRepository. */
	@Mock
	private CitiesRepository citiesRepository;

	/** The WordsService. */
	@Mock
	private WordsService wordsService;

	/** The CitiesService. */
	@InjectMocks
	private CitiesServiceImpl citiesService;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllCitiesTest() {
		Mockito.when(citiesDBRepository.findAll()).thenReturn(Mocks.getListCities());

		List<City> res = citiesService.getAllCities();

		Mockito.verify(citiesDBRepository, Mockito.times(1)).findAll();
		Assert.assertTrue(res.size() > 0);
	}

	@Test
	public void getCitiesByServiceTest() {
		Integer page = 2;
		Integer size = 5;

		Mockito.when(citiesRepository.getCitiesByService(page, size)).thenReturn(Mocks.getCityResponse());

		CityResponse res = citiesService.getCitiesByService(page, size);

		Mockito.verify(citiesRepository, Mockito.times(1)).getCitiesByService(page, size);
		Assert.assertTrue(res.getContent().size() > 0);
	}

	@Test
	public void getCitiesByQueryTest() {
		Integer page = 0;
		Integer size = 2;
		Mockito.when(citiesDBRepository.count()).thenReturn(323L);
		Mockito.when(citiesDBRepository.findAll()).thenReturn(Mocks.getListCities());

		CityResponse res = citiesService.getCitiesByQuery(page, size);

		Mockito.verify(citiesDBRepository, Mockito.times(1)).count();
		Mockito.verify(citiesDBRepository, Mockito.times(1)).findAll();
		Assert.assertTrue(res.getContent().size() > 0);
	}

	@Test
	public void getBestCityTest() {
		Integer length = 5;
		List<String> listCitiesNames = Mocks.getListCitiesNames();
		Mockito.when(citiesDBRepository.getCitiesByLength(length)).thenReturn(listCitiesNames);
		Mockito.when(wordsService.findBestCity(listCitiesNames)).thenReturn("Ronda");

		String res = citiesService.getBestCity(length);

		Mockito.verify(citiesDBRepository, Mockito.times(1)).getCitiesByLength(length);
		Mockito.verify(wordsService, Mockito.times(1)).findBestCity(listCitiesNames);
		Assert.assertTrue(res.equals("Ronda"));

	}

}
