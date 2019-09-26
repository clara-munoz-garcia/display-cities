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

/**
 * Cities API Controller Test
 * 
 * @author clara.munoz
 *
 */
public class CitiesControllerTest {

	/** The Constant APPLICATION_JSON_UTF8. */
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	/** The CitiesService. */
	@Mock
	private CitiesService citiesService;

	/** The CitiesController. */
	@InjectMocks
	private CitiesController citiesApi;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(citiesApi).build();
	}

	@Test
	public void getAllCitiesTest() throws Exception {
		Mockito.when(citiesService.getAllCities()).thenReturn(Mocks.getListCities());

		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.get(EndpointUris.CITIES).contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());

		Mockito.verify(citiesService, Mockito.times(1)).getAllCities();
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}

	@Test
	public void getCitiesByServiceTest() throws Exception {
		Integer page = 0;
		Integer size = 5;
		Mockito.when(citiesService.getCitiesByService(page, size)).thenReturn(Mocks.getCityResponse());

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EndpointUris.CITIES + EndpointUris.SERVICE)
				.param("page", page.toString()).param("size", size.toString())).andExpect(status().isOk());

		Mockito.verify(citiesService, Mockito.times(1)).getCitiesByService(page, size);
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}

	@Test
	public void getCitiesByQueryTest() throws Exception {
		Integer page = 0;
		Integer size = 5;
		Mockito.when(citiesService.getCitiesByQuery(page, size)).thenReturn(Mocks.getCityResponse());

		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.get(EndpointUris.CITIES + EndpointUris.QUERY_BY_PAGE)
						.param("page", page.toString()).param("size", size.toString()))
				.andExpect(status().isOk());

		Mockito.verify(citiesService, Mockito.times(1)).getCitiesByQuery(page, size);
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}

	@Test
	public void getBestCityTest() throws Exception {
		Integer length = 5;
		Mockito.when(citiesService.getBestCity(length)).thenReturn("Algiers");

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(EndpointUris.CITIES + EndpointUris.BEST_CITY)
				.param("length", length.toString())).andExpect(status().isOk());

		Mockito.verify(citiesService, Mockito.times(1)).getBestCity(length);
		assertEquals(HttpStatus.OK.value(), result.andReturn().getResponse().getStatus());
	}
}
