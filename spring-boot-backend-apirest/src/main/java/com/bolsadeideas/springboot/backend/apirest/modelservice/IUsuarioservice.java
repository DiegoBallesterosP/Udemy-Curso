package com.bolsadeideas.springboot.backend.apirest.modelservice;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;

public interface IUsuarioservice {

    public Usuario findByUsername(String username);

}
