package com.company.beers.sgvbackend.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "beer_item", schema = "public")
public class BeerItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "beerItemSeq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "beerItemSeq", sequenceName = "beer_item_seq", allocationSize = 1, initialValue = 1)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "brewery")
	private String brewery;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "price", columnDefinition = "FLOAT(10)")
	private Double price;
	
	@Column(name = "currency")
	private String currency;
	
	public BeerItem() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
