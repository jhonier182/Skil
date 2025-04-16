package com.Skill_Master.Skil.entities;

import com.Skill_Master.Skil.model.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "doc_identidad")
    private String docIdentidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private UserRole rol;

    @Column(name = "ficha")
    private Long ficha;

    @Column(name = "estado")
    private Long estado;

    @Column(name = "profile_picture")
    private String profilePicture;
}
