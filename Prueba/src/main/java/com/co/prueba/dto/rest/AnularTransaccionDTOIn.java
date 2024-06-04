package com.co.prueba.dto.rest;

import java.io.Serializable;

public class AnularTransaccionDTOIn implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long cardId;
	
	private Long transactionId;

	public AnularTransaccionDTOIn() {
	}

	public AnularTransaccionDTOIn(Long cardId, Long transactionId) {
		this.cardId = cardId;
		this.transactionId = transactionId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	

}
