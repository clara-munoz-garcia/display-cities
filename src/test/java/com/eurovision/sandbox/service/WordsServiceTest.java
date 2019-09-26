package com.eurovision.sandbox.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.eurovision.sandbox.mocks.Mocks;
import com.eurovision.sandbox.repository.WordsDBRepository;
import com.eurovision.sandbox.vo.Word;

/**
 * Words Service Test
 * 
 * @author clara.munoz
 */
public class WordsServiceTest {

	/** The WordsDBRepository. */
	@Mock
	private WordsDBRepository wordsDBRepository;

	/** The WordsService. */
	@InjectMocks
	private WordsServiceImpl wordsService;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllWordsTest() {
		Mockito.when(wordsDBRepository.findAll()).thenReturn(Mocks.getListWords());

		List<Word> res = wordsService.getAllWords();

		Mockito.verify(wordsDBRepository, Mockito.times(1)).findAll();
		Assert.assertTrue(res.size() > 0);
	}

	@Test
	public void existsByNameTest() {
		String name = "unlock";
		Mockito.when(wordsDBRepository.existsByName(name)).thenReturn(true);

		boolean res = wordsService.existsByName(name);

		Mockito.verify(wordsDBRepository, Mockito.times(1)).existsByName(name);
		Assert.assertTrue(res);
	}

	@Test
	public void findBestCityTest() {
		Mockito.when(wordsDBRepository.findAllByName()).thenReturn(Mocks.getListCitiesNames());

		String res = wordsService.findBestCity(Mocks.getListCitiesNames());

		Mockito.verify(wordsDBRepository, Mockito.times(1)).findAllByName();
		Assert.assertTrue(res.equals("Cádiz"));
	}

}
