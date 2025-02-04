package com.Skill_Master.Skil.service.user;

import com.Skill_Master.Skil.entities.Ficha;

import java.util.List;
import java.util.Optional;

public interface FichaService {
    // Buscar ficha por número
    Optional<Ficha> findByNumeroFicha(Long numeroFicha);

    // Crear una nueva ficha
    Ficha createFicha(Ficha ficha);

    // Obtener todas las fichas
    List<Ficha> getAllFichas();

    // Actualizar una ficha existente
    Optional<Ficha> updateFicha(Long id, Ficha updatedFicha);

    // Eliminar una ficha
    void deleteFicha(Long id);
}
