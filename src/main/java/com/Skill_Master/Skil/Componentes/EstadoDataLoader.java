package com.Skill_Master.Skil.Componentes;


import com.Skill_Master.Skil.entities.Estado;
import com.Skill_Master.Skil.resposiroty.EstadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EstadoDataLoader implements CommandLineRunner {

    private final EstadoRepository estadoRepository;

    public EstadoDataLoader(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (estadoRepository.count() == 0) {
            Estado activo = new Estado();
            activo.setDescripcion("Activo");
            estadoRepository.save(activo);

            Estado inactivo = new Estado();
            inactivo.setDescripcion("Inactivo");
            estadoRepository.save(inactivo);
        }
    }


}
