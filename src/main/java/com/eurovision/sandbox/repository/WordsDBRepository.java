package com.eurovision.sandbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eurovision.sandbox.vo.Word;

/**
 * Words Database Repository Interface
 * 
 * @author clara.munoz
 */
@Repository
public interface WordsDBRepository extends CrudRepository<Word, Long> {

	/**
	 * Returns true/false if the word exists
	 * 
	 * @param word the word
	 * @return true/false
	 */
	boolean existsByName(String name);

	/**
	 * Returns all the name of the words in dictionary
	 * 
	 * @return List<String>
	 */
	@Query(value = "SELECT words.name FROM words", nativeQuery = true)
	List<String> findAllByName();

}