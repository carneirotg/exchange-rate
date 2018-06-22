package com.code.service.exchange.scheduler;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.code.service.exchange.entities.ForexRate;
import com.code.service.exchange.entities.Rate;
import com.code.service.exchange.repository.ExchangeRateRepository;

@Component
public class ExchangeRateScheduler {
	
	private static final Logger log = LoggerFactory.getLogger(ExchangeRateSchedulerConfig.class);
	
	@Autowired
	private ExchangeRateRepository rRepo;
	
	private RestTemplate rest = new RestTemplate();
	
	@Value("${scheduler.forexUrl}")
	private String forexUrl;

	@Scheduled(fixedRateString = "${scheduler.fetchRatePeriod}")
	public void checkCurrencyRateEURToUSD(){
		ResponseEntity<ForexRate> response = rest.getForEntity(forexUrl, ForexRate.class);
		
		Rate rate = new Rate();
		rate.setCreated(LocalDate.now());
		rate.setRate(response.getBody().getRates().get("USD"));
		
		rRepo.save(rate);
		
	}

}
