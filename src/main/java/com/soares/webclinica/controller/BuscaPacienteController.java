package com.soares.webclinica.controller;

import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.controller.validator.UUIDValidator;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.service.BuscaPacienteService;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger LOGGER = LoggerFactory.getLogger(BuscaPacienteController.class);

	@GetMapping(path = "/paciente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacienteResponseModel> buscaPacientePorId(@RequestParam("id_paciente") String uuid) {
		LOGGER.info("Busca Paciente");
		uuidValidator.validate(uuid).isInvalidThrow(BadRequestException.class);
		Paciente paciente = service.buscaPacientePorId(UUID.fromString(uuid));
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}

	@GetMapping(path = "/paciente/cpf")
	public ResponseEntity<PacienteResponseModel> buscaPacientePorCpf(@RequestParam("cpf") String cpf) {
		LOGGER.info("Busca Paciente por CPF início.");
		Paciente paciente = service.buscaPacientePorCpf(cpf);
		LOGGER.info("Busca Paciente por CPF fim.");
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}

	@GetMapping(path = "/paciente/nome_paciente")
	public ResponseEntity<PacienteResponseModel> buscaPacientePorNome(@RequestParam("nome_paciente") String nomePaciente) {
		Paciente paciente = service.buscaPacientePorNome(nomePaciente);
		return ResponseEntity.ok(PacienteMapper.INSTANCE.mapFrom(paciente));
	}
}
