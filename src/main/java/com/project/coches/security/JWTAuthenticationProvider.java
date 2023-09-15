package com.project.coches.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Creacion y validacion de tokens para iniciar sesion.
@Component
public class JWTAuthenticationProvider {
    @Value("${jwt.secret.key}")
    private String secretKey;

}

