package com.co.prueba.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class DocumentTypeDto implements Serializable {

	private static final long serialVersionUID = 4265082459796579274L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String abbreviation;
	
	@Getter
	@Setter
	private String description;
	
	
	
}
