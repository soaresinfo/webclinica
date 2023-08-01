package com.soares.webclinica.mapper;

import com.soares.webclinica.repository.model.ConsultaEntity;
import com.soares.webclinica.service.model.Consulta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsultaMapper {
    ConsultaMapper INSTANCE = Mappers.getMapper(ConsultaMapper.class);

    ConsultaEntity fromModelToEntity(Consulta source);

    @Mapping(target = "convenio.medico", ignore = true)
    Consulta fromEntityToModel(ConsultaEntity source);
}
