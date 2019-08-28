package com.company.beers.sgvbackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.company.beers.sgvbackend.domain.BeerItem;
import com.company.beers.sgvbackend.domain.PriceBeer;
import com.company.beers.sgvbackend.facade.BeersFacade;

@RestController
@RequestMapping("/beers")
public class BeersController {
	
	@Autowired
	private BeersFacade beersFacade;
	
	@CrossOrigin()
	@GetMapping("")
	public Map<String, Object> searchBeers() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BeerItem> beers = beersFacade.searchBeers();
		map.put("data", beers);
		map.put("totalCount", beers.size());
		map.put("Operacion exitosa", Boolean.TRUE);
		return map;
	}
	
	@CrossOrigin()
	@PostMapping("")
	public Map<String, Object> addBeers(@RequestBody BeerItem beerItem) {
		Map<String, Object> map = new HashMap<String, Object>();
		beersFacade.addBeers(beerItem);
		map.put("message", "Cerveza creada");
		map.put("Operacion exitosa", Boolean.TRUE);
		return map;
	}
	
	@CrossOrigin()
	@GetMapping("/{id}")
	public Map<String, Object> searchBeerById(@PathVariable(value="id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int idNum = Integer.parseInt(id);
		try {
			BeerItem beer = beersFacade.searchBeerById(idNum);
			map.put("data", beer);
			map.put("message", "Operacion exitosa");
			map.put("Operacion exitosa", Boolean.TRUE);
		} catch (NoSuchElementException e) {
			map.put("message", "El Id de la cerveza no existe");
			map.put("Operacion exitosa", Boolean.FALSE);
		}
		return map;
	}
	
	@CrossOrigin()
	@GetMapping("/{id}/boxprice")
	public Map<String, Object> boxBeerPriceById(@PathVariable(value="id") String id, 
			@RequestParam(name = "quantity", defaultValue = "6") int quantity, @RequestParam(name = "currency") String currency) {
		Map<String, Object> map = new HashMap<String, Object>();
		int idNum = Integer.parseInt(id);
		
		try {
			BeerItem beer = beersFacade.searchBeerById(idNum);
			PriceBeer priceBeer = new PriceBeer();
			RestTemplate restTemplate = new RestTemplate();
			
			Map<String, Object> apiResponse = (HashMap<String, Object>) restTemplate.
					getForObject("http://www.apilayer.net/api/live?access_key=5debe7f788f225093362582c99eb4869&format=1", Object.class);
			
			Map<String, Object> quoteResponse = (HashMap<String, Object>)apiResponse.get("quotes");
			Double currencyValue = (Double)quoteResponse.get("USD"+currency.toUpperCase());
			if(quantity == 0) quantity = 6;
			priceBeer.setId(beer.getId());
			priceBeer.setName(beer.getName());
			priceBeer.setCurrency(currency);
			
			Double priceBox = beer.getPrice()*quantity;
			if (beer.getCurrency().equals("USD")) {
				//suponiendo que el currency de BD es USD
				priceBeer.setPriceBox(priceBox*currencyValue);
			} else {
				Double currencyValueBD = (Double)quoteResponse.get("USD"+beer.getCurrency());
				Double priceBoxUSD = priceBox/currencyValueBD;
				priceBeer.setPriceBox(priceBoxUSD*currencyValue);
			}
			
			map.put("data", priceBeer);
			map.put("message", "Operacion exitosa");
			map.put("Operacion exitosa", Boolean.TRUE);
		}  catch (NoSuchElementException e) {
			map.put("message", "El Id de la cerveza no existe");
			map.put("Operacion exitosa", Boolean.FALSE);
		}
		
		return map;
	}
	
}
