package com.eurovision.sandbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eurovision.sandbox.service.CitiesService;
import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities API Controller
 * 
 * @author clara.munoz
 */
@RestController
public class CitiesController implements CitiesApi {

	/**
	 * The CitiesService
	 */
	@Autowired
	private CitiesService citiesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<City>> getAllCities() {
		return new ResponseEntity<List<City>>(citiesService.getAllCities(), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<CityResponse> getCitiesByService(@RequestParam Integer page, @RequestParam Integer size) {
		return new ResponseEntity<CityResponse>(citiesService.getCitiesByService(page, size), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<CityResponse> getCitiesByQuery(@RequestParam Integer page, @RequestParam Integer size) {
		return new ResponseEntity<CityResponse>(citiesService.getCitiesByQuery(page, size), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<String> getBestCity(@RequestParam int length) {
		return new ResponseEntity<String>(citiesService.getBestCity(length), HttpStatus.OK);
	}

}
