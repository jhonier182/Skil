package com.Skill_Master.Skil.service.user;


import com.Skill_Master.Skil.entities.User;
import java.util.List;


public interface UserService {

    User createUser(User user);

    boolean hasUserFirtstEmail(String email);
    
    List<User> getAllUsers();

    User login(User user);

    void deleteUser(Long id);

    List<User> findByFicha(Long ficha);


    void activarUsuario(Long id);

    void desactivarUsuario(Long id);
}

