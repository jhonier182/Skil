package com.Skill_Master.Skil.service;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.model.UserRole;
import com.Skill_Master.Skil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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