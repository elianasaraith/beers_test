package com.company.beers.sgvbackend.service;

import java.util.List;

import com.company.beers.sgvbackend.domain.BeerItem;

public interface BeersService {

	List<BeerItem> searchBeers();
	
	void addBeers(BeerItem beerItem);
	
	BeerItem searchBeerById (int id);
}
