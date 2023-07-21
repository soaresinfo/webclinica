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
        PacienteEntity entity = optional.orElseThrow(() -> {
            ValidationResult result = ValidationResult.fail(List.of(Error.create("id_paciente", "Paciente não encontrado", "422", idPaciente)));
            throw new NotFoundException(result);
        });
        return PacienteMapper.INSTANCE.mapFrom(entity);
    }

    public Paciente buscaPacientePorCpf(String cpf) {
        Optional<PacienteEntity> optional = repository.findByCpf(cpf);
        PacienteEntity entity = optional.orElseThrow(() -> {
            ValidationResult result = ValidationResult.fail(List.of(Error.create("cpf", "Paciente não encontrado", "422", cpf)));
            throw new NotFoundException(result);
        });
        return PacienteMapper.INSTANCE.mapFrom(entity);
    }
}
