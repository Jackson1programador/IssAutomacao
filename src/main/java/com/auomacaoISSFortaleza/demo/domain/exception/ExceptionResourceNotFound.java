package com.auomacaoISSFortaleza.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExceptionResourceNotFound extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExceptionResourceNotFound(String mensagem) {
		super(mensagem);
	}
	

}
