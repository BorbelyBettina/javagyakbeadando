package com.example.javabeadando.security;

import com.example.javabeadando.model.Role;
import com.example.javabeadando.model.User;
import com.example.javabeadando.repository.RoleRepository;
import com.example.javabeadando.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Ellenőrizzük, létezik-e admin
        if (userRepository.findByEmail("admin@admin.com") == null) {

            System.out.println("Admin felhasználó nem található — létrehozás folyamatban...");

            // Admin szerepkör lekérése vagy létrehozása
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ROLE_ADMIN");
                roleRepository.save(adminRole);
            }

            User admin = new User();
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Collections.singleton(adminRole));

            userRepository.save(admin);

            System.out.println("Admin létrehozva: admin@admin.com / admin");
        } else {
            System.out.println("✔ Admin felhasználó már létezik.");
        }
    }
}
