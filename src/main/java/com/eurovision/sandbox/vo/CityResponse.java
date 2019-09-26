package com.eurovision.sandbox.vo;

import java.util.List;

/**
 * CityResponse
 * 
 * @author clara.munoz
 */
public class CityResponse {

	private List<City> content;
	private Integer totalPages;
	private Integer totalElements;
	private boolean last;
	private Integer size;
	private Integer number;

	public List<City> getContent() {
		return content;
	}

	public void setContent(List<City> content) {
		this.content = content;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
