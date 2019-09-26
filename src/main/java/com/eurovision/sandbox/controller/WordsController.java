package com.eurovision.sandbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eurovision.sandbox.service.WordsService;
import com.eurovision.sandbox.vo.Word;

/**
 * Words API Controller
 * 
 * @author clara.munoz
 */
@RestController
public class WordsController implements WordsApi {

	/** The WordsService */
	@Autowired
	private WordsService wordsService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<Word>> getAllWords() {
		return new ResponseEntity<List<Word>>(wordsService.getAllWords(), HttpStatus.OK);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Boolean> existsWord(@RequestParam String word) {
		return new ResponseEntity<Boolean>(wordsService.existsByName(word), HttpStatus.OK);
	}

}
