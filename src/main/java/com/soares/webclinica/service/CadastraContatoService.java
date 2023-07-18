package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.mapper.ContatoMappers;
import com.soares.webclinica.repository.ContatoRepository;
import com.soares.webclinica.repository.model.ContatoEntity;
import com.soares.webclinica.service.model.Contato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastraContatoService {

    private final ContatoRepository repository;

    public Contato cadastraContato(Contato contato) {

        if (repository.findByContato(contato.contato()).isEmpty()){
            ContatoEntity entity = repository.save(ContatoMappers.INSTANCE.fromModelToEntity(contato));
            return ContatoMappers.INSTANCE.fromEntityToModel(entity);
        }

        ValidationResult validation = ValidationResult.fail(List.of(Error.create("Cpf","Contato já cadastrado", "400", contato.contato())));
        throw new BadRequestException(validation);
    }
}
