package com.Skill_Master.Skil.service.user;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.model.UserRole;
import com.Skill_Master.Skil.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdminUser() {
        List<User> admins = userRepository.findByRol(UserRole.ADMIN);
        if (admins.isEmpty()) {
            User user = new User();
            user.setNombre("Admin");
            user.setApellido("Administrator");
            user.setCorreo("admin@skillmaster.com");
            user.setContraseña(passwordEncoder.encode("admin"));
            user.setRol(UserRole.ADMIN);
            user.setEstado(1L); // Estado activo
            
            try {
                userRepository.save(user);
                System.out.println("Usuario administrador creado correctamente.");
            } catch (Exception e) {
                System.err.println("Error al crear el usuario administrador: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public User createUser(User user) {
        if (user.getRol() == null) {
            user.setRol(UserRole.APRENDIZ);
        }
        if (user.getEstado() == null) {
            user.setEstado(1L); // Estado activo por defecto
        }
        user.setContraseña(passwordEncoder.encode(user.getContraseña()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByCorreo(user.getCorreo());
        if (optionalUser.isPresent() && passwordEncoder.matches(user.getContraseña(), optionalUser.get().getContraseña())) {
            return optionalUser.get();
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByFicha(Long ficha) {
        return userRepository.findByFicha(ficha);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRol(role);
    }

    @Override
    public List<User> getUsersByFicha(Long fichaId) {
        return userRepository.findByFicha(fichaId);
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public boolean hasUserFirtstEmail(String email) {
        return userRepository.existsByCorreo(email);
    }

    @Override
    public void activarUsuario(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEstado(1L); // Estado activo
            userRepository.save(user);
        }
    }

    @Override
    public void desactivarUsuario(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEstado(2L); // Estado inactivo
            userRepository.save(user);
        }
    }
}
