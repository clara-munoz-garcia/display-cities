package com.eurovision.sandbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eurovision.sandbox.vo.City;

/**
 * Cities Database Repository Interface
 * 
 * @author clara.munoz
 */
@Repository
public interface CitiesDBRepository extends CrudRepository<City, Long> {

	/**
	 * Returns the cities by length
	 * 
	 * @param length the length of city
	 * @return List<String>
	 */
	@Query(value = "SELECT cities.name FROM cities WHERE length(cities.name) = ?1", nativeQuery = true)
	List<String> getCitiesByLength(int length);
}