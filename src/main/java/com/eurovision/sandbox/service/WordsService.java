package com.eurovision.sandbox.service;

import java.util.List;

import com.eurovision.sandbox.vo.Word;

/**
 * Words Service Interface
 * 
 * @author clara.munoz
 */
public interface WordsService {

	/**
	 * Returns all the name of the words in dictionary
	 * 
	 * @return List<String>
	 */
	List<Word> getAllWords();

	/**
	 * Returns true/false if the word exists
	 * 
	 * @param word the word
	 * @return true/false
	 */
	boolean existsByName(String word);

	/**
	 * Returns the city whose characters form more words in dictionary
	 * 
	 * @param citiesList
	 * @return String
	 */
	String findBestCity(List<String> citiesList);

}
