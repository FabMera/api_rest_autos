package com.project.coches.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.coches.domain.dtos.CustomerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

//Creacion y validacion de tokens para iniciar sesion.
@Component
public class JWTAuthenticationProvider {

    //Llave secreta
    @Value("${jwt.secret.key}")
    private String secretKey;



    //Lista de tokens
    private HashMap<String, CustomerDTO> listTokens = new HashMap<>();

    //Creacion de token
    public String createToken(CustomerDTO customerJWT) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date date = new Date();
        Date validDate = new Date(date.getTime() + 3600000);

        String token = JWT.create()
                .withClaim("fullname", customerJWT.getFullName())
                .withClaim("email", customerJWT.getEmail())
                .withClaim("rol", customerJWT.getRol())
                .withIssuedAt(date)
                .withExpiresAt(validDate)
                .sign(algorithm);
        listTokens.put(token, customerJWT);
        return token;

    }

    //Validacion de token
    public Authentication validateToken(String jwtoken) {
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(jwtoken);
        CustomerDTO existJWTcustomer = listTokens.get(jwtoken);
        if (existJWTcustomer == null) {
            throw new BadCredentialsException("Token invalid");
        }
        HashSet<SimpleGrantedAuthority> rolesAuthorities = new HashSet<>();
        rolesAuthorities.add(new SimpleGrantedAuthority("ROLE" + existJWTcustomer.getRol()));
        return new UsernamePasswordAuthenticationToken(existJWTcustomer, jwtoken, rolesAuthorities);
    }

    //Eliminacion de token
    public String eliminarToken(String jwtoken) {
        if (!listTokens.containsKey(jwtoken)) {
            throw new BadCredentialsException("Token invalid");
        }
        listTokens.remove(jwtoken);
        return "Token eliminado";
    }
}

