package com.soares.webclinica.controller;

import com.github.javafaker.Faker;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.mapper.CommonsMapper;
import com.soares.webclinica.service.BuscaPacienteService;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscaPacienteControllerTest {

	@InjectMocks
	private BuscaPacienteController controller;

	@Mock
	private BuscaPacienteService service;

	static final Faker FAKER = new Faker(new Locale("pt-BR"));

	@Test
	void testBuscaPacientePorIdRetornaStatus200() {
		UUID idPaciente = UUID.randomUUID();
		Paciente paciente = getModel(idPaciente);

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

	private static Paciente getModel(UUID idPaciente) {
		return new Paciente(idPaciente, "Marcelo", LocalDate.now(), FAKER.numerify("###########"), null);
	}

	@Test
	void testBuscaPacientePorIdRetornaIdInvalido() {
		assertThrows(BadRequestException.class,
				() -> controller.buscaPacientePorId("12"));
	}

	@Test
	void testBuscaPacientePorIdNaoEncontraPaciente(){
		when(service.buscaPacientePorId(any())).thenThrow(NotFoundException.class);
		assertThrows(NotFoundException.class,
				() -> controller.buscaPacientePorId(UUID.randomUUID().toString()));
	}

	@Test
	void testBuscaPacientePorCpfSucesso(){
		Paciente paciente = getModel(UUID.randomUUID());
		String cpf = paciente.cpf();

		when(service.buscaPacientePorCpf(eq(cpf))).thenReturn(paciente);

		ResponseEntity<PacienteResponseModel> response = controller.buscaPacientePorCpf(cpf);

		verify(service, times(1)).buscaPacientePorCpf(eq(cpf));

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
		assertThat(response.getBody()).isNotNull()
				.extracting(PacienteResponseModel::getIdPaciente,
						PacienteResponseModel::getNomePaciente,
						PacienteResponseModel::getCpf,
						PacienteResponseModel::getDataNascimento)
				.doesNotContainNull()
				.contains(cpf);
	}

	@Test
	void testBuscaPacientePorCpfENaoEncontraDisparaNotFound(){

		when(service.buscaPacientePorCpf(any())).thenThrow(NotFoundException.class);
		Assertions.assertThrows(NotFoundException.class,
				() -> controller.buscaPacientePorCpf("123"));
	}
}
