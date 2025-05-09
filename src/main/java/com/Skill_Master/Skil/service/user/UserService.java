package com.Skill_Master.Skil.service.user;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User login(User user);
    List<User> findByFicha(Long ficha);
    List<User> getUsersByRole(UserRole role);
    List<User> getUsersByFicha(Long fichaId);
    boolean hasUserFirtstEmail(String email);
    void activarUsuario(Long id);
    void desactivarUsuario(Long id);
}

