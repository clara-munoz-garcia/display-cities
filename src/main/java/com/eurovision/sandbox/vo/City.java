package com.eurovision.sandbox.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City Entity
 * 
 * @author clara.munoz
 *
 */
@Entity
@Table(name = "cities")
public class City {

	@Id
	Integer id;

	@Column(name = "name")
	String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
