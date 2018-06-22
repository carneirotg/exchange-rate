package com.code.service.exchange.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.code.service.exchange.entities.Rate;


public interface ExchangeRateRepository extends CrudRepository<Rate, Long>{

	Rate findFirstByOrderByCreatedDesc();
	
	List<Rate> findAllByCreatedBetween(Date startDate, Date endDate);
	
}
