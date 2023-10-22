package com.tadhack.healthclick.controller;


import com.tadhack.healthclick.model.Login;
import com.tadhack.healthclick.model.Token;
import com.tadhack.healthclick.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "http://localhost:4200")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Login loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginDto.getUsername());
        Token token = new Token();
        token.setAccess_token(jwt);
        token.setUsername(loginDto.getUsername());
        token.setEmail(loginDto.ge);
        return ResponseEntity.ok(token);
        //return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
