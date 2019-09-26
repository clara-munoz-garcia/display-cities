package com.eurovision.sandbox.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eurovision.sandbox.controller.uris.EndpointUris;
import com.eurovision.sandbox.mocks.Mocks;
import com.eurovision.sandbox.service.CitiesService;
import com.eurovision.sandbox.service.WordsService;

/**
 * Words API Controller Test
 * 
 * @author clara.munoz
 */
public class WordsControllerTest {

	/** The Constant APPLICATION_JSON_UTF8. */
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	/** The WordsService */
	@Mock
	private WordsService wordsService;

	/** The CitiesService. */
	@Mock
	private CitiesService citiesService;

	/** The WordsController. */
	@InjectMocks
	private WordsController wordsApi;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(wordsApi).build();
	}

	@Test
	public void getAllWordsTest() throws Exception {
		Mockito.when(wordsService.getAllWords()).thenReturn(Mocks.getListWords());

		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.get(EndpointUris.WORDS).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		Mockito.verify(wordsService, Mockito.times(1)).getAllWords();
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}

	@Test
	public void existsWordTest() throws Exception {
		String word = "unlock";
		Mockito.when(wordsService.existsByName(word)).thenReturn(true);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EndpointUris.WORDS + EndpointUris.EXISTS)
				.param("word", word).contentType(APPLICATION_JSON_UTF8)).andExpect(status().isOk());

		Mockito.verify(wordsService, Mockito.times(1)).existsByName(word);
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}

}
