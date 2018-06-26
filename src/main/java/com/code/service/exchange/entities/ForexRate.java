package com.code.service.exchange.entities;

import java.util.Map;

public class ForexRate {
	
	private Map<String, Double> rates;
	
	private String date;

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
