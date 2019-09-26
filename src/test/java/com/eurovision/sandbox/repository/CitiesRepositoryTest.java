package com.eurovision.sandbox.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Repository Test
 * 
 * @author clara.munoz
 */
public class CitiesRepositoryTest {

	/** The Constant URI. */
	private static final String URI = "http://sandbox-1.westeurope.cloudapp.azure.com:8081";

	/**
	 * The RestTemplate
	 */
	@Mock
	private RestTemplate restTemplate;

	/** The CitiesRepository. */
	@InjectMocks
	private CitiesRepositoryImpl citiesRepository;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getCitiesByServiceTest() {
		Integer page = 2;
		Integer size = 5;

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI + "api/cities/queryByPage")
				.queryParam("page", page).queryParam("size", size);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<CityResponse> resEntity = new ResponseEntity<CityResponse>(
				com.eurovision.sandbox.mocks.Mocks.getCityResponse(), HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.eq(builder.toUriString()), Mockito.eq(HttpMethod.GET),
				Mockito.eq(entity), Mockito.any(ParameterizedTypeReference.class))).thenReturn(resEntity);

		CityResponse res = citiesRepository.getCitiesByService(page, size);
		Assert.assertTrue(res.getContent().size() > 0);
	}
}
