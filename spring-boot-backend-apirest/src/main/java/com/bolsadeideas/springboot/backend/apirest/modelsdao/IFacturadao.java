package com.bolsadeideas.springboot.backend.apirest.modelsdao;

import com.bolsadeideas.springboot.backend.apirest.models.Factura;

import org.springframework.data.repository.CrudRepository;

public interface IFacturadao extends CrudRepository<Factura, Long> {

}
