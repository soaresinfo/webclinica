package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.repository.PacienteRepository;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BuscaPacienteService {

    private final PacienteRepository repository;

    public Paciente buscaPacientePorId(UUID idPaciente) {
        Optional<PacienteEntity> optional = repository.findById(idPaciente);
        optional.orElseThrow(() -> {
            ValidationResult result = ValidationResult.fail(List.of(Error.create("id_paciente", "Erro", "422", idPaciente)));
            throw new NotFoundException(result);
        });
        return PacienteMapper.INSTANCE.mapFrom(optional.get());
    }
}
