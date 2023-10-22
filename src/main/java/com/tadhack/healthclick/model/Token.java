package com.tadhack.healthclick.model;


import lombok.Data;

@Data
public class Token {
    private String access_token;
    private String username;
    private String email;
}
