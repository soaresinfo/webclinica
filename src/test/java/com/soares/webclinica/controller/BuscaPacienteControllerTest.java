package com.soares.webclinica.controller;

import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.mapper.CommonsMapper;
import com.soares.webclinica.service.BuscaPacienteService;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscaPacienteControllerTest {

	@InjectMocks
	private BuscaPacienteController controller;

	@Mock
	private BuscaPacienteService service;

	@Test
	void testBuscaPacientePorIdRetornaStatus200() {
		UUID idPaciente = UUID.randomUUID();
		Paciente paciente = new Paciente(idPaciente, "Marcelo", LocalDate.now(), "123", null);

		when(service.buscaPacientePorId(any(UUID.class))).thenReturn(paciente);
		ResponseEntity<PacienteResponseModel> retorno = controller.buscaPacientePorId(idPaciente.toString());

		assertThat(retorno).isNotNull();
		assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(retorno.getBody()).isNotNull();

		PacienteResponseModel response = retorno.getBody();
		assertThat(response.getIdPaciente()).isEqualTo(idPaciente.toString());
		assertThat(response.getNomePaciente()).isEqualTo(paciente.nomePaciente());
		assertThat(response.getDataNascimento()).isEqualTo(CommonsMapper.DATE_FORMATTER.format(paciente.dataNascimento()));
		assertThat(response.getCpf()).isEqualTo(paciente.cpf());
	}

	@Test
	void testBuscaPacientePorIdRetornaIdInvalido() {
		Exception exception = assertThrows(BadRequestException.class,
				() -> controller.buscaPacientePorId("12"));
	}

	@Test
	void testBuscaPacientePorIdNaoEncontraPaciente(){
		when(service.buscaPacientePorId(any())).thenThrow(NotFoundException.class);
		Exception exception = assertThrows(NotFoundException.class,
				() -> controller.buscaPacientePorId(UUID.randomUUID().toString()));
	}
}
