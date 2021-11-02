package com.bolsadeideas.springboot.backend.apirest.modelsdao;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientedao extends JpaRepository<Cliente, Long> {

}