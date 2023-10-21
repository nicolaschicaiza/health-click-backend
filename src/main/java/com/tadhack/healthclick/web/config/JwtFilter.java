package com.tadhack.healthclick.web.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        //inyetando user datail service, tambien se refiere al user security service porque
        //la esta implementando
        this.userDetailsService = userDetailsService;
    }
    //validacion de jwt
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. Validar que sea un Header Authorization valido, que no este vacio
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            //sera una peticion invalida
            return;
        }

        // 2. Validar que el JWT sea valido
        String jwt = authHeader.split(" ")[1].trim();

        if (!this.jwtUtil.isValid(jwt)) {
            filterChain.doFilter(request, response);
            //si jwt no es valido
            return;
        }

        // 3. Cargar el usuario del UserDetailsService, dentro de la base de mysql
        String username = this.jwtUtil.getUsername(jwt);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        // 4. Cargar al usuario en el contexto de seguridad.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword(), user.getAuthorities()
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //cargar al contexto
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("imprimir autencionacion token: "+authenticationToken);
        filterChain.doFilter(request, response);
    }
}

