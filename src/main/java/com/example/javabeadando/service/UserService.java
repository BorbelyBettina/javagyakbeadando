package com.example.javabeadando.service;

import com.example.javabeadando.model.Role;
import com.example.javabeadando.model.User;
import com.example.javabeadando.repository.RoleRepository;
import com.example.javabeadando.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void registerUser(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        // alap szerepkör PDF szerint: ROLE_USER
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
    }
}
