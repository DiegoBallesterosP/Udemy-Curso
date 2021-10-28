package com.bolsadeideas.springboot.backend.apirest.modelsdao;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClientedao extends CrudRepository<Cliente, Long> {

}