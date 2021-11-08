package com.bolsadeideas.springboot.backend.apirest.Auth;

import java.util.HashMap;
import java.util.Map;

import com.bolsadeideas.springboot.backend.apirest.models.Usuario;
import com.bolsadeideas.springboot.backend.apirest.modelservice.IUsuarioservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

@Component
public class Infoadicionaltoken implements TokenEnhancer {

    @Autowired
    private IUsuarioservice usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("info adicional", "Hola que tal!".concat(authentication.getName()));

        info.put("apellidos", usuario.getNombre());
        info.put("email_usuario", usuario.getApellidos());
        info.put("nombre_usuario", usuario.getEmail());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }

}
