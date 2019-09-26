package com.eurovision.sandbox.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eurovision.sandbox.controller.uris.EndpointUris;
import com.eurovision.sandbox.vo.Word;

/**
 * Words API Interface
 * 
 * @author clara.munoz
 */
@RequestMapping(EndpointUris.WORDS)
public interface WordsApi {

	/**
	 * Gets all words
	 * 
	 * @return List<Word>
	 */
	@GetMapping
	ResponseEntity<List<Word>> getAllWords();

	/**
	 * Returns true/false if the word exists
	 * 
	 * @param word the word
	 * @return true/false
	 */
	@GetMapping(EndpointUris.EXISTS)
	ResponseEntity<Boolean> existsWord(String word);

}
