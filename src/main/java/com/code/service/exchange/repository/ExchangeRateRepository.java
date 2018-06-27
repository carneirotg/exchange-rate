package com.code.service.exchange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.code.service.exchange.entities.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long>{

	ExchangeRate findFirstByOrderByCreatedDesc();
	
	List<ExchangeRate> findAllByCreatedBetweenOrderByCreatedAsc(LocalDate startDate, LocalDate endDate);
	
}
