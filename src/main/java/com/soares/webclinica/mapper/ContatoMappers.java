package com.soares.webclinica.mapper;

import com.soares.webclinica.repository.model.ContatoEntity;
import com.soares.webclinica.service.model.Contato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContatoMappers {

    ContatoMappers INSTANCE = Mappers.getMapper(ContatoMappers.class);

    ContatoEntity fromModelToEntity(Contato source);

    Contato fromEntityToModel(ContatoEntity source);
}
