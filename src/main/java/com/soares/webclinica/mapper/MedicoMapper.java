package com.soares.webclinica.mapper;

import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.controller.model.MedicoResponseModel;
import com.soares.webclinica.repository.model.MedicoEntity;
import com.soares.webclinica.service.model.Medico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicoMapper {

    MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

    MedicoEntity fromModelToEntity(Medico source);

    Medico fromEntityToModel(MedicoEntity source);

    Medico fromRequestToModel(MedicoRequestModel source);

    MedicoResponseModel fromModelToResponse(Medico medico);
}
