package com.example.jwtauthentication.service;

import com.example.jwtauthentication.entity.User;
import com.example.jwtauthentication.exception.UsernameNotFoundException;

public interface UserDetailsService {
    void saveUser(User user);
    User getUserByNameAndPassword(String name, String password) throws UsernameNotFoundException;
}
