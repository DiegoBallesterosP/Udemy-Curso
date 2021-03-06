package com.bolsadeideas.springboot.backend.apirest.modelservice;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.Factura;
import com.bolsadeideas.springboot.backend.apirest.models.Producto;
import com.bolsadeideas.springboot.backend.apirest.models.Region;
import com.bolsadeideas.springboot.backend.apirest.modelsdao.IClientedao;
import com.bolsadeideas.springboot.backend.apirest.modelsdao.IFacturadao;
import com.bolsadeideas.springboot.backend.apirest.modelsdao.IProductodao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Clienteservicelmpl implements IClienteservice {

    @Autowired
    private IClientedao clientedao;

    @Autowired
    private IFacturadao facturaDao;

    @Autowired
    private IProductodao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clientedao.findAll();
    }

    @Override
    @Transactional
    public Page<Cliente> findAll(Pageable pageable) {
        return clientedao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(long id) {
        return clientedao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientedao.deleteById(id);

    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clientedao.save(cliente);
    }

    public String upload_folder = ".//src//main//resources//files//";
    // src//main//resources//files//

    public void saveFile(MultipartFile file, Long id) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload_folder + file.getOriginalFilename());
            Files.write(path, bytes);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegiones() {

        return clientedao.findAllRegiones();
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {

        return facturaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Factura saveFactura(Factura factura) {

        return facturaDao.save(factura);
    }

    @Override
    @Transactional
    public void deleteFacturaById(Long id) {
        facturaDao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findProductoByNombre(String term) {
        return productoDao.findByNombreContainingIgnoreCase(term);
    }

}
