package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.dto.SaleDto;
import com.co.prueba.dto.SaleDtoPutPost;
import com.co.prueba.exception.StoreException;
import com.co.prueba.service.SaleService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	private SaleService service;

	@PostMapping
	public SaleDto createSale(@RequestBody SaleDtoPutPost saleDto) throws StoreException {
		return service.createOrUpdate(saleDto);
	}
	
	@PutMapping
	public SaleDto updateSale(@RequestBody SaleDtoPutPost saleDto) throws StoreException {
		return service.createOrUpdate(saleDto);
	}
	
	@GetMapping
	public SaleDto findById(@RequestParam(value = "id") String id) throws StoreException {
		return service.findSale(id);
	}
}
