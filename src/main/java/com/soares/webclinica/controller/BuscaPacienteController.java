package com.soares.webclinica.controller;

import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.controller.validator.UUIDValidator;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.service.BuscaPacienteService;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class BuscaPacienteController {

	private final UUIDValidator uuidValidator = new UUIDValidator("id_paciente");

	private final BuscaPacienteService service;

	@GetMapping(path = "/paciente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacienteResponseModel> buscaPacientePorId(@RequestParam("id_paciente") String uuid) {

		uuidValidator.validate(uuid).isInvalidThrow(BadRequestException.class);
		Paciente paciente = service.buscaPacientePorId(UUID.fromString(uuid));
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}

	@GetMapping(path = "/paciente/cpf")
	public ResponseEntity<PacienteResponseModel> buscaPacientePorCpf(@RequestParam("cpf") String cpf) {
		Paciente paciente = service.buscaPacientePorCpf(cpf);
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}

	@GetMapping(path = "/paciente/nome_paciente")
	public ResponseEntity<PacienteResponseModel> buscaPacientePorNome(@RequestParam("nome_paciente") String nomePaciente) {
		Paciente paciente = service.buscaPacientePorNome(nomePaciente);
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}
}
