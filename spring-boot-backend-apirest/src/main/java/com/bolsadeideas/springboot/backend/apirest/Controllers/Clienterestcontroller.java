package com.bolsadeideas.springboot.backend.apirest.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import com.bolsadeideas.springboot.backend.apirest.models.Cliente;
import com.bolsadeideas.springboot.backend.apirest.modelservice.IClienteservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class Clienterestcontroller {

    @Autowired
    private IClienteservice clienteservice;

    private final Logger log = LoggerFactory.getLogger(Clienterestcontroller.class);

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteservice.findAll();
    }

    // se adiciona para para la paginacion
    // ----------------------------------------------------------------------------------------------------
    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return clienteservice.findAll(pageable);
    }

    // controlador de consulta
    // ----------------------------------------------------------------------------------------------------
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteservice.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realiza la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    // controlador de Creacion
    // ----------------------------------------------------------------------------------------------------
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result, Cliente cliente2) {

        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            cliente2 = clienteservice.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido creado con éxito!");
        response.put("cliente", clienteNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // controlador de Actualiza
    // ----------------------------------------------------------------------------------------------------
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

        Cliente clienteActual = clienteservice.findById(id);

        Cliente clienteUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (clienteActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            clienteActual.setApellidos(cliente.getApellidos());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());

            clienteUpdated = clienteservice.save(clienteActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito!");
        response.put("cliente", clienteUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // controlador de Eliminar
    // ----------------------------------------------------------------------------------------------------
    @DeleteMapping("/clientes/{id}")

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {

            Cliente cliente = clienteservice.findById(id);

            String nombreFotoAnterior = cliente.getFoto();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("files").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    archivoFotoAnterior.delete();
                }
            }

            clienteservice.delete(id);
        }

        catch (DataAccessException e) {

            response.put("mensaje", "Error al eliminar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El cliente ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // controlador de CARGA
    // ----------------------------------------------------------------------------------------------------

    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteservice.findById(id);
        if (!file.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace("", "");

            Path rutaArchivo = Paths.get("files").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());

            try {
                Files.copy(file.getInputStream(), rutaArchivo);

            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del cliente" + nombreArchivo);

                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = cliente.getFoto();

            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("files").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    archivoFotoAnterior.delete();
                }
            }

            cliente.setFoto(nombreArchivo);
            clienteservice.save(cliente);
            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imangen" + nombreArchivo);

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @GetMapping("/files/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> VerFoto(@PathVariable String nombreFoto) {

        Path rutaArchivo = Paths.get("files").resolve(nombreFoto).toAbsolutePath();
        Resource recurso = null;

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

    }
}
