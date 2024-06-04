package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.dto.rest.AnularTransaccionDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.dto.rest.TransaccionDTOOut;
import com.co.prueba.service.TransaccionService;
import com.co.prueba.utils.Constantes;

@RestController
@RequestMapping("/transaction")
public class TransaccionController {

	@Autowired
	private TransaccionService service;
	
	@PostMapping("/enroll")
	public ResponseEntity<EntityResponse<TransaccionDTOOut>> realizarCompra(@RequestBody TransaccionDTOIn compra){
		return service.agregarTransaccion(compra, Constantes.TIPO_TRANSACCION_COMPRA);
	}
	
	@GetMapping("/{transactionId}")
	public ResponseEntity<EntityResponse<TransaccionDTOOut>> consultarTransaccion(@PathVariable("transactionId")  Long transactionId){
		return service.consultarTransaccion(transactionId);
	}
	
	@PostMapping("/anulation")
	public ResponseEntity<EntityResponse<HttpStatus>> anularTransaccion(@RequestBody AnularTransaccionDTOIn anularTransaccionDTOIn){
		return service.anularTransaccion(anularTransaccionDTOIn);
	}
	
}
