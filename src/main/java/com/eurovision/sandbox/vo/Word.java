package com.eurovision.sandbox.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Word Entity
 * 
 * @author clara.munoz
 */
@Entity
@Table(name = "words")
public class Word {

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

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true; // if both pointing towards same object on heap

		Word a = (Word) o;
		return this.name.equalsIgnoreCase(a.name);
	}
}
