package com.example.jwtauthentication.service.serviceImpl;

import com.example.jwtauthentication.entity.User;
import com.example.jwtauthentication.exception.UsernameNotFoundException;
import com.example.jwtauthentication.repository.UserRepository;
import com.example.jwtauthentication.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        User userDetail = new User();
        userDetail.setUsername(user.getUsername());
        userDetail.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userDetail);
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(name);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username " + name, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        if(user.isPasswordValid(password)) {
            return user;
        }
        return null;
    }
}
