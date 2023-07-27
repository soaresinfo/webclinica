package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.mapper.MedicoMapper;
import com.soares.webclinica.repository.MedicoRepository;
import com.soares.webclinica.repository.model.MedicoEntity;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BuscaMedicoService {

    private final MedicoRepository repository;


    public Medico buscaPorNome(String nomeMedico) {
        Optional<MedicoEntity> optional = repository.findByNomeMedico(nomeMedico);
        MedicoEntity entity = optional.orElseThrow(() -> {
            ValidationResult result = ValidationResult.fail(List.of(Error.create(MedicoRequestModel.NOME_MEDICO, "Médico não encontrado", "422", nomeMedico)));
            return new NotFoundException(result);
        });
        return MedicoMapper.INSTANCE.fromEntityToModel(entity);
    }

    public List<Medico> buscaTodos() {
        List<MedicoEntity> entityList = repository.findAll();
        return MedicoMapper.INSTANCE.fromListEntityToListModel(entityList);
    }
}
