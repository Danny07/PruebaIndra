package com.co.prueba.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "document_type")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class DocumentType implements Serializable {

	private static final long serialVersionUID = 3706170749991148947L;

	@Id
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	@Column
	private String abbreviation;
	
	@Getter
	@Setter
	@Column
	private String description;
	
	
	
}
