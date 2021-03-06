package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.Region;

public interface IClienteservice {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public Cliente findById(long id);

    public Cliente save(Cliente clienteActual);

    public void delete(Long id);

    public void saveFile(MultipartFile file, Long id) throws IOException;

    public List<Region> findAllRegiones();

    public Factura findFacturaById(Long id);

    public Factura saveFactura(Factura factura);

    public void deleteFacturaById(Long id);

    public List<Producto> findProductoByNombre(String term);
}
