package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.dto.SaleDto;
import com.co.prueba.dto.SaleDtoPost;
import com.co.prueba.dto.SaleDtoPut;
import com.co.prueba.exception.StoreException;
import com.co.prueba.service.SaleService;


@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	private SaleService service;

	@PostMapping
	public SaleDto createSale(@RequestBody SaleDtoPost saleDto) throws StoreException {
		return service.create(saleDto);
	}
	
	@PutMapping
	public SaleDto updateSale(@RequestBody SaleDtoPut saleDto) throws StoreException {
		return service.update(saleDto);
	}
	
	@GetMapping
	public SaleDto findById(@RequestParam(value = "id") String id) throws StoreException {
		return service.findSale(id);
	}
}
