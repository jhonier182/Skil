package com.Skill_Master.Skil.resposiroty;

import com.Skill_Master.Skil.entities.Ficha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface FichaRepository extends JpaRepository <Ficha, Long> {
    Optional<Ficha> findByNumeroFicha(Long numeroFicha);
}
