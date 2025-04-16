package com.Skill_Master.Skil.repository;

import com.Skill_Master.Skil.entities.User;
import com.Skill_Master.Skil.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRol(UserRole rol);
    
    Optional<User> findByCorreo(String correo);
    boolean existsByCorreo(String correo);

    //buscar por ficha
    @Query("SELECT u FROM User u WHERE u.ficha = :ficha")
    List<User> findByFicha(@Param("ficha") Long ficha);

    List<User> findByEstado(Long estado);
} 