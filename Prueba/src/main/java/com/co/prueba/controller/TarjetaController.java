package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.dto.rest.ActivarTarjetaDTOIn;
import com.co.prueba.dto.rest.EntityResponse;
import com.co.prueba.dto.rest.NumeroTarjetaDTOOut;
import com.co.prueba.dto.rest.TarjetaDTOOut;
import com.co.prueba.dto.rest.TransaccionDTOIn;
import com.co.prueba.service.TarjetaService;

@RestController
@RequestMapping("/card")
public class TarjetaController {
	
	@Autowired
	private TarjetaService service;
	
	
	@GetMapping("/{productId}/number")
	public ResponseEntity<EntityResponse<NumeroTarjetaDTOOut>> generarNumeroTarjeta(@PathVariable("productId") Long productId) {
		return service.generarNumeroTarjeta(productId);
	}
	
	@PostMapping("/enroll")
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> activarTarjeta(@RequestBody ActivarTarjetaDTOIn activarTarjetaDTOIn){
		return service.activarTarjeta(activarTarjetaDTOIn);
	}
	
	@DeleteMapping("/{cardId}")
	public ResponseEntity<EntityResponse<HttpStatus>> bloqueoTarjeta(@PathVariable("cardId") Long cardId){
		return service.bloquearTarjeta(cardId);
	}
	
	@PostMapping("/balance")
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> recargarSaldo(@RequestBody TransaccionDTOIn recargaSaldoDTOIn) {
		return service.recagarSaldo(recargaSaldoDTOIn);
	}
	
	@GetMapping("/balance/{cardId}")
	public ResponseEntity<EntityResponse<TarjetaDTOOut>> consultarSaldo(@PathVariable("cardId") Long cardId){
		return service.consultarSaldo(cardId);
	}
	
	
	
	

}
