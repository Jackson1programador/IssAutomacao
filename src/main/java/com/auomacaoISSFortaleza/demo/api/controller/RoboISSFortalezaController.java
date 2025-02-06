package com.auomacaoISSFortaleza.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.auomacaoISSFortaleza.demo.robo.IssFortaleza;

@RestController
@RequestMapping("/iss-fortaleza")
public class RoboISSFortalezaController {
	
	@Autowired
	private IssFortaleza issFortaleza;
	
	@GetMapping(value="/{idEmpresa}/{idCliente}")
	@ResponseStatus(value = HttpStatus.OK)
	public void  encerrarIssFortaleza(@PathVariable Long idEmpresa, @PathVariable Long idCliente) {
		issFortaleza.executarEncerramento(idEmpresa, idCliente); 
	}

}
