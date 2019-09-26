package com.eurovision.sandbox.service;

import java.util.List;

import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Service Interface
 * 
 * @author clara.munoz
 */
public interface CitiesService {

	/**
	 * Gets all the cities
	 * 
	 * @return List<City>
	 */
	List<City> getAllCities();

	/**
	 * Gets the filtered cities from service
	 * 
	 * @param page the page number
	 * @param size the size number
	 * @return CityResponse
	 */
	CityResponse getCitiesByService(Integer page, Integer size);

	/**
	 * Gets the filtered cities
	 * 
	 * @param page the page number
	 * @param size the size number
	 * @return CityResponse
	 */
	CityResponse getCitiesByQuery(Integer page, Integer size);

	/**
	 * Gets the city whose characters form more words in dictionary
	 * 
	 * @param wordsLength length of cities list
	 * @return the best city name
	 */
	String getBestCity(int length);

}
