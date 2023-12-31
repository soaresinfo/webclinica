package com.soares.webclinica.mapper;

import com.soares.webclinica.controller.model.PacienteRequestModel;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.service.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ResponsavelMapper.class})
public interface PacienteMapper {

    static final PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    Paciente mapFrom(PacienteEntity source);

    PacienteResponseModel mapFrom(Paciente source);

    PacienteEntity fromModelToEntity(Paciente source);

    Paciente fromRequestToModel(PacienteRequestModel model);
}
