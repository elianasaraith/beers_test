package com.company.beers.sgvbackend.domain;

public class PriceBeer {

	private int id;
	private String name;
	private String currency;
	private Double priceBox;

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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getPriceBox() {
		return priceBox;
	}
	public void setPriceBox(double d) {
		this.priceBox = d;
	}
	
	public PriceBeer() {
		super();
	}
	
	
}
