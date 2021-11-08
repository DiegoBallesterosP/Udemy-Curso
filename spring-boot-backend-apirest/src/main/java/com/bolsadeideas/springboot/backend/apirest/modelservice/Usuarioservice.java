package com.bolsadeideas.springboot.backend.apirest.modelservice;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;
import com.bolsadeideas.springboot.backend.apirest.modelsdao.IUsuariodao;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Usuarioservice implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(Usuarioservice.class);

    @Autowired
    private IUsuariodao usuarioDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            logger.error("error en el login: no existe el usuario'" + username + "' en el sistema!");
            throw new UsernameNotFoundException(
                    "error en el login: no existe el usuario'" + username + "' en el sistema!");

        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
                authorities);

    }

}
