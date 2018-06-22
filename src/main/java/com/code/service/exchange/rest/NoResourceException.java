package com.code.service.exchange.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResourceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoResourceException(){
		super("There is no resource in the database");
	}
}
