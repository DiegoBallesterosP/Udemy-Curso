package com.bolsadeideas.springboot.backend.apirest.Controllers;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.Producto;
import com.bolsadeideas.springboot.backend.apirest.modelservice.IClienteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class Facturarestcontroller {

    @Autowired
    private IClienteservice clienteservice;

    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id) {
        return clienteservice.findFacturaById(id);

    }

    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clienteservice.deleteFacturaById(id);
    }

    @GetMapping("/facturas/filtrar-productos/{term}")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> filtrarProductos(@PathVariable String term) {
        return clienteservice.findProductoByNombre(term);

    }
}
