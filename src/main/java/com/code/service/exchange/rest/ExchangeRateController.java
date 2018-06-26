package com.code.service.exchange.rest;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.code.service.exchange.entities.Rate;
import com.code.service.exchange.repository.ExchangeRateRepository;

@RestController
@RequestMapping("/exchangerate/v1/rates")
public class ExchangeRateController {
	
	private static final Logger log = LoggerFactory.getLogger(ExchangeRateController.class);
	
	@Autowired
	private ExchangeRateRepository rRepo;
	
	/***
	 * Endpoint to retrieve the latest rate polled from the website configured in scheduler.forexUrl
	 * @return Rate
	 * @throws NoResourceException
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = {"/latest"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Rate find() throws NoResourceException{
		
		Rate r = rRepo.findFirstByOrderByCreatedDesc();
		
		if (r == null){
			throw new NoResourceException();
		}
		
		return r;
	}
	
	
	/***
	 * Endpoint to retrieve a list of historical rates between a start date and an end date.
	 * @param Date yyyy-MM-dd startDate
	 * @param Date yyyy-MM-dd endDate
	 * @return List<Rate>
	 * @throws IllegalArgumentException
	 * @throws InvalidArgumentException 
	 */
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = {"/history"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Rate> findHistorical(@RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
								   , @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd")   LocalDate endDate) 
					  throws InvalidArgumentException{
		log.debug("find called startDate: ["+startDate+"] and endDate: ["+endDate+"]");
		
		if (startDate.isAfter(endDate)){
			throw new InvalidArgumentException("The start_date should be before the end_date");
		}
		
		List<Rate> rates = rRepo.findAllByCreatedBetweenOrderByCreatedAsc(startDate, endDate);
		
		return rates;
	}
	
}
