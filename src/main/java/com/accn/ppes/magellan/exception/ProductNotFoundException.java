package com.accn.ppes.magellan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long productID) {
		super("No such Product: " + productID);
	}

	public ProductNotFoundException(String name) {
		// TODO Auto-generated constructor stub
		super("No such Product: " + name);
	}
}
