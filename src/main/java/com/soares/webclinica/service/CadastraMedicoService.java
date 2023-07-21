package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.mapper.MedicoMapper;
import com.soares.webclinica.repository.MedicoRepository;
import com.soares.webclinica.repository.model.MedicoEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastraMedicoService {

    private final MedicoRepository repository;

    public Medico cadastraMedico(Medico medico) {

        MedicoEntity entityToSave = MedicoMapper.INSTANCE.fromModelToEntity(medico);

        if (repository.findByCrm(medico.crm()).isEmpty()){
            return MedicoMapper.INSTANCE.fromEntityToModel(repository.save(entityToSave));
        }

        ValidationResult validation = ValidationResult.fail(List.of(Error.create("crm","Médico já cadastrado",
                String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), medico.crm())));
        throw new UnprocessableEntityException(validation);

    }
}
