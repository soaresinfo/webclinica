package com.soares.webclinica.controller;

import com.github.javafaker.Faker;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.PacienteRequestModel;
import com.soares.webclinica.controller.model.PacienteResponseModel;
import com.soares.webclinica.controller.model.ResponsavelRequestModel;
import com.soares.webclinica.mapper.CommonsMapper;
import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.service.CadastraPacienteService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraPacienteControllerTest {

    @InjectMocks
    private CadastraPacienteController controller;

    @Mock
    private CadastraPacienteService service;

    private static final Faker FAKER = new Faker(new Locale("pt-BR"));

    @Test
    void testCadastraPacienteRetornaSucesso(){
        PacienteRequestModel model = getPacienteRequestModel();
        Paciente modelSaved = PacienteMapper.INSTANCE.fromRequestToModel(model);
        Paciente modelReturned = getModelReturned(modelSaved);

        when(service.cadastraPaciente(eq(modelSaved))).thenReturn(modelReturned);

        ResponseEntity<PacienteResponseModel> response = controller.cadastraPaciente(model);

        verify(service, times(1)).cadastraPaciente(any(Paciente.class));

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody())
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .extracting(PacienteResponseModel::getNomePaciente,
                            PacienteResponseModel::getDataNascimento,
                            PacienteResponseModel::getCpf)
                .containsExactly(model.getNomePaciente(),
                                 model.getDataNascimento(),
                                 model.getCpf());
    }

    @Test
    void testCadastraPacienteJaExistenteDevolveBadRequest(){
        PacienteRequestModel requestModel = getPacienteRequestModel();
        requestModel.setNomePaciente("123456789012345678901234567890123456789012345678901");

        Exception exception = Assertions.assertThrows(BadRequestException.class,
                () -> controller.cadastraPaciente(requestModel));

        verify(service, never()).cadastraPaciente(any(Paciente.class));
    }

    private static Paciente getModelReturned(Paciente modelSaved) {
        return new Paciente(UUID.randomUUID(), modelSaved.nomePaciente(),
                modelSaved.dataNascimento(), modelSaved.cpf(), modelSaved.responsavel());
    }

    private static PacienteRequestModel getPacienteRequestModel() {
        return PacienteRequestModel.builder()
                .nomePaciente(FAKER.lordOfTheRings().character())
                .dataNascimento(LocalDate.now().format(CommonsMapper.DATE_FORMATTER))
                .cpf(FAKER.numerify("###########"))
                .responsavel(ResponsavelRequestModel.builder()
                                .nomeResponsavel(FAKER.lordOfTheRings().character())
                                .dataNascimento(LocalDate.now().format(CommonsMapper.DATE_FORMATTER))
                                .cpf(FAKER.numerify("###########"))
                                .build())
                .build();
    }
}
