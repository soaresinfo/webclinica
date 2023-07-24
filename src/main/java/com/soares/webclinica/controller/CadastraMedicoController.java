package com.soares.webclinica.controller;

import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.controller.model.MedicoResponseModel;
import com.soares.webclinica.controller.validator.MedicoRequestValidator;
import com.soares.webclinica.mapper.MedicoMapper;
import com.soares.webclinica.service.CadastraMedicoService;
import com.soares.webclinica.service.model.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class CadastraMedicoController {

    private final CadastraMedicoService service;

    private final MedicoRequestValidator validator;

    public ResponseEntity<MedicoResponseModel> cadastraMedico(MedicoRequestModel requestModel) {
        validator.validate(requestModel).isInvalidThrow(BadRequestException.class);
        Medico medico = service.cadastraMedico(MedicoMapper.INSTANCE.fromRequestToModel(requestModel));
        return ResponseEntity.accepted().body(MedicoMapper.INSTANCE.fromModelToResponse(medico));
    }
}
