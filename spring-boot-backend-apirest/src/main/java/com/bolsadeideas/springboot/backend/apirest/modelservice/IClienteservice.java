package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;

public interface IClienteservice {

    public List<Cliente> findAll();

    public Cliente findById(long id);

    public Cliente save(Cliente cliente);

    public void delete(Long id);

}
