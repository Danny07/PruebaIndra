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
public class UserDto implements Serializable {

	private static final long serialVersionUID = 6557358500474412317L;

	@Getter
	@Setter
    private String id;
	
	@Getter
	@Setter
	private String documentId;
	
	@Getter
	@Setter
	private String documentType;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String lastName;
	
	@Getter
	@Setter
	private String email;
	

}
