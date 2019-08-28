package com.company.beers.sgvbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.beers.sgvbackend.dao.BeersRepository;
import com.company.beers.sgvbackend.domain.BeerItem;
import com.company.beers.sgvbackend.service.BeersService;

@Service
@Transactional("transactionManager")
public class BeersServiceImpl implements BeersService {

	@Autowired
	private BeersRepository beersRepository;
	
	@Override
	public List<BeerItem> searchBeers() {
		return beersRepository.findAll();
	}

	@Override
	public void addBeers(BeerItem beerItem) {
		if(beerItem.getId() != 0) {
			//update
			BeerItem beerStored = beersRepository.getOne(beerItem.getId());
			beerStored.setId(beerItem.getId());
			beerStored.setName(beerItem.getName());
			beerStored.setBrewery(beerItem.getBrewery());
			beerStored.setCountry(beerItem.getCountry());
			beerStored.setCurrency(beerItem.getCurrency());
			beerStored.setPrice(beerItem.getPrice());
			beersRepository.save(beerStored);
		} else {
			//add
			beersRepository.save(beerItem);
		}
		
	}

	@Override
	public BeerItem searchBeerById(int id) {
		Optional<BeerItem> beerItem = beersRepository.findById(id);
		return beerItem.get();
	}

	
}
