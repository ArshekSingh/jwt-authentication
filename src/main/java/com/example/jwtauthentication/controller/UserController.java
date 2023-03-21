package com.example.jwtauthentication.controller;

import com.example.jwtauthentication.config.JwtGeneratorInterface;
import com.example.jwtauthentication.entity.User;
import com.example.jwtauthentication.exception.UsernameNotFoundException;
import com.example.jwtauthentication.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtGeneratorInterface jwtGenerator;

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        try {
            if (!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
                throw new UsernameNotFoundException("username or password is empty");
            }
            User userDetail = userDetailsService.getUserByNameAndPassword(user.getUsername(), user.getPassword());
            if (userDetail == null) {
                throw new UsernameNotFoundException("username or password is invalid", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UsernameNotFoundException{
        if(!StringUtils.hasText(user.getUsername()) || !StringUtils.hasText(user.getPassword())) {
            throw new UsernameNotFoundException("username or password is empty", HttpStatus.BAD_REQUEST);
        }
        userDetailsService.saveUser(user);
        return new ResponseEntity<>("user save successfully", HttpStatus.OK);
    }
}
