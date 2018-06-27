package com.code.service.exchange.scheduler;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.service.exchange.entities.ExchangeRate;
import com.code.service.exchange.entities.ForexRate;
import com.code.service.exchange.repository.ExchangeRateRepository;

@Component
public class ExchangeRateScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(ExchangeRateScheduler.class);
	
	@Autowired
	private ExchangeRateRepository rRepo;
	
	private RestTemplate rest = new RestTemplate();
	
	@Value("${scheduler.forexUrl}")
	private String forexUrl;

	@Value("${scheduler.fetchRatePeriod}")
	private String fetchRatePeriod;


	@Scheduled(fixedRateString = "${scheduler.fetchRatePeriod}")
	public void checkCurrencyRateEURToUSD(){
		log.info("Polling server in [{}] ms to retrieve exchange rate...", fetchRatePeriod);
		ResponseEntity<ForexRate> response = rest.getForEntity(getForexUrl(), ForexRate.class);
		
		ExchangeRate rate = new ExchangeRate();
		rate.setCreated(LocalDate.parse(response.getBody().getDate()));
		
		rate.setRate(BigDecimal.valueOf(response.getBody().getRates().get("USD")));
		
		rRepo.save(rate);
		
	}
	
	public String getForexUrl() {
		return forexUrl;
	}

}
