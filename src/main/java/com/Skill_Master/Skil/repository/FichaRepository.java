package com.Skill_Master.Skil.repository;

import com.Skill_Master.Skil.entities.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
    List<Ficha> findAllByPrograma(String programa);
    Optional<Ficha> findByNumeroFicha(Long numeroFicha);
} 