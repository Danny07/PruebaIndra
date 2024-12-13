package com.co.prueba.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -4609784521093878249L;
	
	@Id
	@Getter
	@Setter
    @GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Getter
	@Setter
	@Column(name = "document_id")
	private String documentId;
	
	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_document_type")
	private DocumentType documentType;
	
	@Getter
	@Setter
	@Column
	private String name;
	
	@Getter
	@Setter
	@Column(name = "last_name")
	private String lastName;
	
	@Getter
	@Setter
	@Column
	private String email;
	
	@Getter
	@Setter
	@Column(name = "create_data")
	private Date createData;
	
	@Getter
	@Setter
	@Column(name = "update_data")
	private Date updateData;
	

}
