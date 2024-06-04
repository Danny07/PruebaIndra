package com.co.prueba.dto.rest;

import java.io.Serializable;

public class ActivarTarjetaDTOIn implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long cardId;

	public ActivarTarjetaDTOIn() {
	}

	public ActivarTarjetaDTOIn(Long cardId) {
		this.cardId = cardId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

}
