package com.co.prueba.dto.rest;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class TransaccionDTOIn implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Long cardId;
	
	@NotNull
	private BigDecimal balance;

	public TransaccionDTOIn() {
	}

	public TransaccionDTOIn(Long cardId, BigDecimal balance) {
		this.cardId = cardId;
		this.balance = balance;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
