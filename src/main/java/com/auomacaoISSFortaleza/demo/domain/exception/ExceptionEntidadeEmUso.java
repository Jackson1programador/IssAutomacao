package com.auomacaoISSFortaleza.demo.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExceptionEntidadeEmUso extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExceptionEntidadeEmUso(String mensagem) {
		super(mensagem);
	}
	

}
