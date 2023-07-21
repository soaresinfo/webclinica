package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.repository.PacienteRepository;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastraPacienteService {

    private final PacienteRepository repository;

    public Paciente cadastraPaciente(Paciente model) {

        if (repository.findByCpf(model.cpf()).isEmpty()){
            PacienteEntity entity = PacienteMapper.INSTANCE.fromModelToEntity(model);
            return PacienteMapper.INSTANCE.mapFrom(repository.save(entity));
        }

        ValidationResult validation = ValidationResult.fail(List.of(Error.create("Cpf","Paciente já cadastrado", "400", model.cpf())));
        throw new UnprocessableEntityException(validation);
    }
}
