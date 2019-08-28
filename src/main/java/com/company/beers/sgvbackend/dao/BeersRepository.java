package com.company.beers.sgvbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.beers.sgvbackend.domain.BeerItem;

@Repository
public interface BeersRepository extends JpaRepository<BeerItem, Integer>{

}
