package com.code.service.exchange.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.code.service.exchange.entities.Rate;

@Repository
public interface ExchangeRateRepository extends CrudRepository<Rate, Long>{

	Rate findFirstByOrderByCreatedDesc();
	
	List<Rate> findAllByCreatedBetweenOrderByCreatedAsc(LocalDate startDate, LocalDate endDate);
	
}
