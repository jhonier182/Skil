package com.Skill_Master.Skil.repository;

import com.Skill_Master.Skil.entities.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Estado findByDescripcion(String descripcion);
} 