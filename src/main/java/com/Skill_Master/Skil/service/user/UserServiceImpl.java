package com.Skill_Master.Skil.service.user;

import com.Skill_Master.Skil.Enums.UserRole;
import com.Skill_Master.Skil.entities.Estado;
import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.resposiroty.EstadoRepository;
import com.Skill_Master.Skil.resposiroty.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private EstadoRepository estadoRepository;


    // Crear usuario administrador al iniciar la aplicación (si no existe)
    @PostConstruct
    public void createAdminUser() {
        User admin = userRepository.findByRole(UserRole.ADMIN);
        if (admin == null) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@gmail.com");
            user.setPassword("admin");
            user.setRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }

    // Validar si un correo ya existe en el sistema
    public boolean hasUserFirtstEmail(String email) {
        return userRepository.findFirtstByEmail(email) != null;
    }

    // Crear usuarios
    @Override
    public User createUser(User user) {
        if (user.getRole() == null) {
            user.setRole(UserRole.APRENDIZ); // Rol predeterminado: APRENDIZ
        }
        return userRepository.save(user);
    }

    // Traer todos los usuarios
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Login de usuario
    @Override
    public User login(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent() && user.getPassword().equals(optionalUser.get().getPassword())) {
            return optionalUser.get();
        }
        return null;
    }

    // Eliminar usuario
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Buscar usuarios por ficha
    @Override
    public List<User> findByFicha(Long ficha) {
        return userRepository.findByFicha(ficha);
    }




    // Cambiar el estado de un usuario
    public void cambiarEstado(Long userId, String estadoDescripcion) {
        // Buscar el usuario por ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar el estado correspondiente
        Estado estado = estadoRepository.findByDescripcion(estadoDescripcion);
        if (estado == null) {
            throw new RuntimeException("Estado no válido");
        }

        // Asignar el nuevo estado al usuario
        user.setEstado(estado);

        // Guardar el usuario con el nuevo estado
        userRepository.save(user);
    }


    // Cambiar a estado activo
    public void activarUsuario(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Estado estadoActivo = estadoRepository.findByDescripcion("Activo");
            if (estadoActivo != null) {
                user.setEstado(estadoActivo);
                userRepository.save(user);
            }
        }
    }


    // Cambiar a estado inactivo
    public void desactivarUsuario(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Estado estadoInactivo = estadoRepository.findByDescripcion("Inactivo");
            if (estadoInactivo != null) {
                user.setEstado(estadoInactivo);
                userRepository.save(user);
            }
        }
    }


}
