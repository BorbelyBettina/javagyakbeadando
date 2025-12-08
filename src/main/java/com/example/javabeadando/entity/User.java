package com.example.javabeadando.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Név megadása kötelező")
    private String name;

    @NotBlank(message = "Email megadása kötelező")
    @Email(message = "Érvénytelen email cím")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Jelszó megadása kötelező")
    @Size(min = 6, message = "Jelszó legalább 6 karakter")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.registered;

    private LocalDateTime emailVerifiedAt;
    private String rememberToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Role {
        registered, admin
    }
}
