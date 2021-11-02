package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteservice {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findById(long id);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

}
