package com.eurovision.sandbox.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.eurovision.sandbox.vo.CityResponse;

/**
 * Cities Repository Class
 * 
 * @author clara.munoz
 */
@Repository
public class CitiesRepositoryImpl implements CitiesRepository {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CitiesRepositoryImpl.class);

	/** The Constant URI. */
	private static final String URI = "http://sandbox-1.westeurope.cloudapp.azure.com:8081";

	/**
	 * The RestTemplate
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CityResponse getCitiesByService(Integer page, Integer size) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URI + "/api/cities/queryByPage")
				.queryParam("page", page).queryParam("size", size);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		CityResponse response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				new ParameterizedTypeReference<CityResponse>() {
				}).getBody();
		return response;
	}
}
