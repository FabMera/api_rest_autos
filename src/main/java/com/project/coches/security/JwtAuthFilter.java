package com.project.coches.security;

import com.project.coches.exceptions.UnAuthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTAuthenticationProvider jwtAuthenticationProvider;

    private final List<String> urlPermitted = List.of("/auth", "/swagger-ui.html", "/swagger-ui", "/api-docs");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Revisamos las cabeceras del request para ver si existe el token y si es valido.
        String header = request.getHeader(TokenJWTConfig.HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TokenJWTConfig.TOKEN_PREFIX)) {
            throw new UnAuthorizedException();
        }

        String[] headerSplit = header.split(" ");
        if (headerSplit.length != 2) {
            throw new UnAuthorizedException();
        }
        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(headerSplit[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            throw new ServletException(e.getMessage());
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //Hacer match con las url permitidas,verificar si la url del request esta en la lista de url permitidas
        return urlPermitted.stream().anyMatch(url -> request.getRequestURI().contains(url));
    }
}
