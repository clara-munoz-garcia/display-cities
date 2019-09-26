package com.eurovision.sandbox.mocks;

import java.util.ArrayList;
import java.util.List;

import com.eurovision.sandbox.vo.City;
import com.eurovision.sandbox.vo.CityResponse;
import com.eurovision.sandbox.vo.Word;

/**
 * The Class Mocks.
 */
public class Mocks {

	/**
	 * Gets the list of cities.
	 *
	 * @return List<City>
	 */
	public static List<City> getListCities() {
		List<City> cities = new ArrayList<City>();
		City city = new City();
		city.setId(1);
		city.setName("Moscú");
		cities.add(city);
		City city2 = new City();
		city2.setId(2);
		city2.setName("Madrid");
		cities.add(city2);
		City city3 = new City();
		city3.setId(3);
		city3.setName("Praga");
		cities.add(city3);
		City city4 = new City();
		city4.setId(4);
		city4.setName("París");
		cities.add(city4);
		return cities;
	}

	/**
	 * Gets CityResponse object.
	 *
	 * @return CityResponse
	 */
	public static CityResponse getCityResponse() {
		CityResponse cityResponse = new CityResponse();
		cityResponse.setContent(getListCities());
		cityResponse.setLast(false);
		cityResponse.setNumber(1);
		cityResponse.setSize(5);
		cityResponse.setTotalElements(323);
		cityResponse.setTotalPages(67);
		return cityResponse;
	}

	/**
	 * Gets the list of words.
	 *
	 * @return List<Word>
	 */
	public static List<Word> getListWords() {
		List<Word> words = new ArrayList<Word>();
		Word word = new Word();
		word.setId(1);
		word.setName("unlock");
		words.add(word);
		Word word2 = new Word();
		word2.setId(2);
		word2.setName("clown");
		words.add(word2);
		Word word3 = new Word();
		word3.setId(3);
		word3.setName("clock");
		words.add(word3);
		return words;
	}

	/**
	 * Gets the list of cities.
	 *
	 * @return List<String>
	 */
	public static List<String> getListCitiesNames() {
		List<String> list = new ArrayList<>();
		list.add("Praga");
		list.add("Cádiz");
		list.add("Ronda");
		return list;
	}

}
