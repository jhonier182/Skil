package com.Skill_Master.Skil.service;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.model.UserRole;
import com.Skill_Master.Skil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        // Verificar si el correo ya existe
        if (userRepository.existsByCorreo(user.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Encriptar la contraseña
        user.setContraseña(passwordEncoder.encode(user.getContraseña()));

        // Asignar rol por defecto si no se especifica
        if (user.getRol() == null) {
            user.setRol(UserRole.APRENDIZ);
        }

        // Asignar estado activo por defecto
        if (user.getEstado() == null) {
            user.setEstado(1L);
        }

        // Guardar el usuario
        return userRepository.save(user);
    }

    public User login(String correo, String contraseña) {
        User user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(contraseña, user.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        if (user.getEstado() != 1L) {
            throw new RuntimeException("Usuario inactivo");
        }

        return user;
    }
} 