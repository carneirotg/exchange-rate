package com.code.service.exchange.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "RATE")
public class ExchangeRate {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private LocalDate created;
	
	@Column(precision = 9, scale = 4)
	private BigDecimal rate;
	
	public ExchangeRate(){
		
	}
	
	public ExchangeRate(LocalDate created, BigDecimal rate){
		this.created = created;
		this.rate = rate;
	}
	

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
