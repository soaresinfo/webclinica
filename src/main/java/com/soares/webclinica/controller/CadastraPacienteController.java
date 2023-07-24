package com.soares.webclinica.controller;

import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteRequestModel;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.controller.validator.PacienteRequestValidator;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.service.CadastraPacienteService;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CadastraPacienteController {

    private final PacienteRequestValidator validator;

    private final CadastraPacienteService service;

    @PostMapping(path = "/paciente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PacienteResponseModel> cadastraPaciente(@RequestBody PacienteRequestModel request){
        validator.validate(request).isInvalidThrow(BadRequestException.class);
        Paciente response = service.cadastraPaciente(PacienteMapper.INSTANCE.fromRequestToModel(request));
        return ResponseEntity.accepted().body(PacienteMapper.INSTANCE.mapFrom(response));
    }
}
