package com.bolsadeideas.springboot.backend.apirest.modelsdao;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuariodao extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    @Query("select u from Usuario u where u.username=?1")
    public Usuario findByUsername2(String username);

}
