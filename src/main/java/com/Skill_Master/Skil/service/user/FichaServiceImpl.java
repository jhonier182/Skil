package com.Skill_Master.Skil.service.user;

import com.Skill_Master.Skil.entities.Ficha;
import com.Skill_Master.Skil.repository.FichaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaServiceImpl implements FichaService {

    @Autowired
    private FichaRepository fichaRepository;

    // Buscar ficha por número
    @Override
    public Optional<Ficha> findByNumeroFicha(Long numeroFicha) {
        return fichaRepository.findByNumeroFicha(numeroFicha);
    }

    // Crear una nueva ficha
    @Override
    public Ficha createFicha(Ficha ficha) {
        return fichaRepository.save(ficha);
    }

    // Obtener todas las fichas
    @Override
    public List<Ficha> getAllFichas() {
        return fichaRepository.findAll();
    }

    // Actualizar una ficha existente
    @Override
    public Optional<Ficha> updateFicha(Long id, Ficha updatedFicha) {
        return fichaRepository.findById(id).map(ficha -> {
            ficha.setNumeroFicha(updatedFicha.getNumeroFicha());
            ficha.setNombreFicha(updatedFicha.getNombreFicha());  // Aseguramos que el campo 'nombreFicha' sea actualizado
            return fichaRepository.save(ficha);
        });
    }

    // Eliminar una ficha
    @Override
    public void deleteFicha(Long id) {
        fichaRepository.deleteById(id);
    }
}
