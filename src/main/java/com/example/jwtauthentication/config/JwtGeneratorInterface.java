package com.example.jwtauthentication.config;

import com.example.jwtauthentication.entity.User;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(User user);
}
