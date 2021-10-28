package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.modelsdao.IClientedao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Clienteservicelmpl implements IClienteservice {

    @Autowired
    private IClientedao clientedao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clientedao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(long id) {
        return clientedao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clientedao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientedao.deleteById(id);

    }
}
