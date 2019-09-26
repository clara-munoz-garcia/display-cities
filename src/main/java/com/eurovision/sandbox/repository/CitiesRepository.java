package com.eurovision.sandbox.repository;

import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Repository Interface
 * 
 * @author clara.munoz
 */
public interface CitiesRepository {

	/**
	 * Returns cities using WS
	 * 
	 * @param page the page number
	 * @param size the size number
	 * @return CityResponse
	 */
	CityResponse getCitiesByService(Integer page, Integer size);
}
