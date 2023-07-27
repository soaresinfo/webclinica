package com.soares.webclinica.controller;

import com.github.javafaker.Faker;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.factory.PacienteFactory;
import com.soares.webclinica.mapper.CommonsMapper;
import com.soares.webclinica.service.BuscaPacienteService;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
import com.soares.webclinica.util.FakerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

	private static final Faker FAKER = FakerFactory.getInstance();

	@Test
	void testBuscaPacientePorIdRetornaStatus200() {
		UUID idPaciente = UUID.randomUUID();
		Paciente paciente = PacienteFactory.getModel(idPaciente);

		when(service.buscaPacientePorId(any(UUID.class))).thenReturn(paciente);
		ResponseEntity<PacienteResponseModel> retorno = controller.buscaPacientePorId(idPaciente.toString());

		verify(service, only()).buscaPacientePorId(eq(idPaciente));

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
		assertThrows(BadRequestException.class,
				() -> controller.buscaPacientePorId("12"));
	}

	@Test
	void testBuscaPacientePorIdNaoEncontraPaciente(){
		when(service.buscaPacientePorId(any())).thenThrow(NotFoundException.class);
		assertThrows(NotFoundException.class,
				() -> controller.buscaPacientePorId(UUID.randomUUID().toString()));

		verify(service, only()).buscaPacientePorId(any());
	}

	@Test
	void testBuscaPacientePorCpfSucesso(){
		Paciente paciente = PacienteFactory.getModel();
		String cpf = paciente.cpf();

		when(service.buscaPacientePorCpf(eq(cpf))).thenReturn(paciente);

		ResponseEntity<PacienteResponseModel> response = controller.buscaPacientePorCpf(cpf);

		verify(service, only()).buscaPacientePorCpf(eq(cpf));

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

		verify(service, only()).buscaPacientePorCpf(any());
	}

	@Test
	void testBuscaPacientePorNomeRetornaPaciente(){

		Paciente paciente = PacienteFactory.getModel();
		String nomePaciente = paciente.nomePaciente();
		when(service.buscaPacientePorNome(eq(nomePaciente))).thenReturn(paciente);

		ResponseEntity<PacienteResponseModel> response = controller.buscaPacientePorNome(nomePaciente);

		verify(service, only()).buscaPacientePorNome(eq(nomePaciente));

		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody())
				.isNotNull()
				.extracting(PacienteResponseModel::getNomePaciente,
							PacienteResponseModel::getCpf,
							PacienteResponseModel::getIdPaciente,
							PacienteResponseModel::getDataNascimento)
				.contains(nomePaciente)
				.doesNotContainNull();

	}

	@Test
	void testBuscaPacientePorNomeDisparaNotFound(){
		String nomePaciente = FAKER.name().fullName();
		when(service.buscaPacientePorNome(eq(nomePaciente))).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class,
				() -> controller.buscaPacientePorNome(nomePaciente));

		verify(service, only()).buscaPacientePorNome(eq(nomePaciente));
	}
}
