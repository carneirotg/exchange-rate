package com.code.service.exchange.base;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.code.service.exchange.repository.ExchangeRateRepository;

@Configuration
public class ExchangeRateConfig {
  	
  	@Bean
  	public ExchangeRateRepository robotRepository(){
  		return Mockito.mock(ExchangeRateRepository.class);
  	}
}