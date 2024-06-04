package com.co.prueba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.co.prueba.entity.TipoTransaccion;

public interface TipoTransaccionRepository extends CrudRepository<TipoTransaccion, Long> {

	@Query("SELECT   t FROM tipo_transaccion t where t.tipo = ?1")
	TipoTransaccion findByTipo(String tipo);
}
