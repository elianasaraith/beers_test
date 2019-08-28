package com.company.beers.sgvbackend.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.beers.sgvbackend.domain.BeerItem;
import com.company.beers.sgvbackend.facade.BeersFacade;
import com.company.beers.sgvbackend.service.BeersService;

@Component("beersFacade")
public class BeersFacadeImpl implements BeersFacade {

	@Autowired
	private BeersService beersService;
	
	@Override
	public List<BeerItem> searchBeers() {
		return beersService.searchBeers();
	}

	@Override
	public void addBeers(BeerItem beerItem) {
		beersService.addBeers(beerItem);
	}

	@Override
	public BeerItem searchBeerById(int id) {
		return beersService.searchBeerById(id);
	}

}
