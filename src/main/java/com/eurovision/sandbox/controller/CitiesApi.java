package com.eurovision.sandbox.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eurovision.sandbox.controller.uris.EndpointUris;
import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities API Interface
 * 
 * @author clara.munoz
 */
@RequestMapping(EndpointUris.CITIES)
public interface CitiesApi {

	/**
	 * Gets all the cities
	 * 
	 * @return List<City>
	 */
	@GetMapping
	ResponseEntity<List<City>> getAllCities();

	/**
	 * Gets the filtered cities from service
	 * 
	 * @param page the page number
	 * @param size the size number
	 * @return CityResponse
	 */
	@GetMapping(EndpointUris.SERVICE)
	ResponseEntity<CityResponse> getCitiesByService(Integer page, Integer size);

	/**
	 * Gets the filtered cities
	 * 
	 * @param page the page number
	 * @param size the size number
	 * @return CityResponse
	 */
	@GetMapping(EndpointUris.QUERY_BY_PAGE)
	ResponseEntity<CityResponse> getCitiesByQuery(Integer page, Integer size);

	/**
	 * Gets the city whose characters form more words in dictionary
	 * 
	 * @param wordsLength length of cities list
	 * @return the best city name
	 */
	@GetMapping(EndpointUris.BEST_CITY)
	ResponseEntity<String> getBestCity(int wordsLength);

}
