package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;

public interface IClienteservice {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findById(long id);

    public Cliente save(Cliente clienteActual);

    public void delete(Long id);

    public void saveFile(MultipartFile file) throws IOException;
}
