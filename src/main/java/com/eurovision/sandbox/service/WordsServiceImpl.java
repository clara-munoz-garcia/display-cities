package com.eurovision.sandbox.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurovision.sandbox.repository.WordsDBRepository;
import com.eurovision.sandbox.vo.Word;

/**
 * Words Service Class
 * 
 * @author clara.munoz
 */
@Service
public class WordsServiceImpl implements WordsService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(WordsServiceImpl.class);

	/** The Constant LENGTH_5. */
	private static final int LENGTH_5 = 5;

	/** The Constant LENGTH_6. */
	private static final int LENGTH_6 = 6;

	/** The Constant LENGTH_7. */
	private static final int LENGTH_7 = 7;

	/** The WordsDBRepository. */
	@Autowired
	private WordsDBRepository wordsDBRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Word> getAllWords() {
		List<Word> words = new ArrayList<>();
		wordsDBRepository.findAll().forEach(words::add);
		return words;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsByName(String name) {
		return wordsDBRepository.existsByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findBestCity(List<String> wordsList) {
		Map<String, HashSet<String>> finalList = new HashMap<>();
		for (String word : wordsList) {
			HashSet<String> permutations = combineAndSwap(word);
			finalList.put(word, new HashSet<String>(permutations));
		}
		finalList.forEach((k, v) -> LOG.info("City: " + k + " - Permutations: " + v));
		return comparingDictionary(finalList);
	}

	/**
	 * Makes the calls to the combine and swap methods to obtain the permutation set
	 * of the received word
	 * 
	 * @param word the received word
	 * @return the permutation set
	 */
	private HashSet<String> combineAndSwap(String word) {
		// Used to not allow repetitions in permutations
		HashSet<String> wordsSet = new HashSet<>();
		HashSet<String> combinations = new HashSet<>();
		StringBuilder result = new StringBuilder();
		// Obtains the combinations of the word
		combine(0, word, combinations, result);
		for (String combination : combinations) {
			// Obtains the swaps
			permute(combination, "", wordsSet);
		}
		return wordsSet;

	}

	/**
	 * Returns all possible combinations of 5, 6 and 7 letters of the received word
	 * 
	 * @param start      the start index
	 * @param word       the received word
	 * @param combinations the result list
	 * @param wordResult the combination result
	 */
	private void combine(int start, String word, HashSet<String> combinations, StringBuilder wordResult) {
		// Walks all the letters of the word combining and adding one more char until
		// max length
		for (int i = start; i < word.length(); ++i) {
			wordResult.append(word.charAt(i));
			// If the length is what we want, then we get it
			if (wordResult.length() == LENGTH_5 || wordResult.length() == LENGTH_6 || wordResult.length() == LENGTH_7) {
				combinations.add(wordResult.toString().toLowerCase());
			}
			// We continue combining
			if (i < word.length()) {
				combine(i + 1, word, combinations, wordResult);
			}
			wordResult.setLength(wordResult.length() - 1);
		}
	}

	/**
	 * Returns all possible permutations of a received word without repetitions
	 * 
	 * @param word     the received word
	 * @param result   the result of permutation
	 * @param wordsSet the permutations set
	 */
	private void permute(String word, String result, Set<String> wordsSet) {
		if (word.length() == 0) {
			// We add the permutation to result
			wordsSet.add(result);
			return;
		}
		// Walks all the letters of the word
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			// Makes the swap
			String resAux = word.substring(0, i) + word.substring(i + 1);
			permute(resAux, result + character, wordsSet);
		}
	}

	/**
	 * Compares and returns the city whose characters form more words in dictionary
	 * 
	 * @param finalList the final list with all possible permutations
	 * @return the best city
	 */
	private String comparingDictionary(Map<String, HashSet<String>> finalList) {
		Map<String, Integer> countingWordsMap = new HashMap<>();
		List<String> dictionary = wordsDBRepository.findAllByName();
		Set<Entry<String, HashSet<String>>> set = finalList.entrySet();
		Iterator<Entry<String, HashSet<String>>> iterator = set.iterator();
		// Walks for all the hashmap with city name and permutations
		while (iterator.hasNext()) {
			Entry<String, HashSet<String>> mentry = iterator.next();
			String wordName = mentry.getKey();
			List<String> permList = ((HashSet<String>) mentry.getValue()).stream().collect(Collectors.toList());
			Set<String> rightWords = new HashSet<>();
			for (String permutation : permList) {
				// Compares the permutation with the dictionary
				if (dictionary.stream().anyMatch(str -> str.trim().equalsIgnoreCase(permutation))) {
					rightWords.add(permutation.toLowerCase());
				}
			}
			LOG.info("City: " + wordName + " - Words: " + rightWords.toString());
			countingWordsMap.put(wordName, rightWords.size());
		}

		// Sorts the map and returns the first position
		Map<String, Integer> sortedByCount = countingWordsMap.entrySet().stream()
				.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		countingWordsMap.forEach((k, v) -> LOG.info("City : " + k + " - Total: " + v));
		return sortedByCount.keySet().stream().findFirst().get();
	}
}
