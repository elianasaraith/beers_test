package com.company.beers.sgvbackend.facade;

import java.util.List;

import com.company.beers.sgvbackend.domain.BeerItem;

public interface BeersFacade {

	List<BeerItem> searchBeers();
	
	void addBeers(BeerItem beerItem);
	
	BeerItem searchBeerById (int id);
	
}
