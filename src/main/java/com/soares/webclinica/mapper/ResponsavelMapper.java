package com.soares.webclinica.mapper;

import com.soares.webclinica.controller.model.ResponsavelRequestModel;
import com.soares.webclinica.controller.model.ResponsavelResponseModel;
import com.soares.webclinica.service.model.Responsavel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponsavelMapper {

    static final ResponsavelMapper INSTANCE = Mappers.getMapper(ResponsavelMapper.class);

    Responsavel fromRequestToModel(ResponsavelRequestModel source);

    ResponsavelResponseModel fromModelToResponse(Responsavel source);
}
