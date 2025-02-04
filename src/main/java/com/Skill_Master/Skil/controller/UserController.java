package com.Skill_Master.Skil.controller;

import com.Skill_Master.Skil.Enums.UserRole;
import com.Skill_Master.Skil.entities.Estado;
import com.Skill_Master.Skil.entities.Ficha;
import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.resposiroty.EstadoRepository;
import com.Skill_Master.Skil.resposiroty.UserRepository;
import com.Skill_Master.Skil.service.user.FichaService;
import com.Skill_Master.Skil.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FichaService fichaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstadoRepository estadoRepository;


    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userService.hasUserFirtstEmail(user.getEmail())) {
            return new ResponseEntity<>("El email ya está en uso.", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getRole() == null) {
            return new ResponseEntity<>("El rol es obligatorio.", HttpStatus.BAD_REQUEST);
        }

        if (UserRole.INSTRUCTOR.equals(user.getRole())) {
            // Crear Instructor
        } else if (UserRole.APRENDIZ.equals(user.getRole())) {
            if (user.getFicha() == null || user.getFicha().getNumeroFicha() == null) {
                return new ResponseEntity<>("El número de ficha es obligatorio para el rol APRENDIZ.", HttpStatus.BAD_REQUEST);
            }
            Optional<Ficha> fichaOptional = fichaService.findByNumeroFicha(user.getFicha().getNumeroFicha());
            if (fichaOptional.isEmpty()) {
                return new ResponseEntity<>("La ficha proporcionada no existe.", HttpStatus.BAD_REQUEST);
            }
            user.setFicha(fichaOptional.get());
        } else {
            return new ResponseEntity<>("Rol no válido. Debe ser 'APRENDIZ' o 'INSTRUCTOR'.", HttpStatus.BAD_REQUEST);
        }

        // Asignar estado "Activo" por defecto
        Estado estadoActivo = estadoRepository.findByDescripcion("Activo");
        if (estadoActivo != null) {
            user.setEstado(estadoActivo);
        }

        User createdUser = userService.createUser(user);
        return createdUser != null
                ? new ResponseEntity<>(createdUser, HttpStatus.CREATED)
                : new ResponseEntity<>("Usuario no creado, intente de nuevo.", HttpStatus.NOT_ACCEPTABLE);
    }




    // Obtener todos los usuarios
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = userService.login(user);
        return dbUser != null
                ? new ResponseEntity<>(dbUser, HttpStatus.OK)
                : new ResponseEntity<>("Credenciales Erróneas.", HttpStatus.NOT_ACCEPTABLE);
    }

    // Eliminar usuario
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Buscar aprendices por ficha
    @GetMapping("/users/ficha/{ficha}")
    public ResponseEntity<List<User>> findByFicha(@PathVariable Long ficha) {
        List<User> users = userService.findByFicha(ficha);
        return ResponseEntity.ok(users);
    }



    @PutMapping("/{id}/activar")
    public void activarUsuario(@PathVariable Long id) {
        userService.activarUsuario(id);
    }

    @PutMapping("/{id}/desactivar")
    public void desactivarUsuario(@PathVariable Long id) {
        userService.desactivarUsuario(id);
    }

}
