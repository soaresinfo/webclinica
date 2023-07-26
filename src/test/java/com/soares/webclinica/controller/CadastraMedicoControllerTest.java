package com.soares.webclinica.controller;

import br.com.fluentvalidator.context.Error;
import com.github.javafaker.Faker;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.controller.model.MedicoResponseModel;
import com.soares.webclinica.controller.validator.MedicoRequestValidator;
import com.soares.webclinica.factory.MedicoFactory;
import com.soares.webclinica.service.CadastraMedicoService;
import com.soares.webclinica.service.model.Medico;
import com.soares.webclinica.util.FakerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraMedicoControllerTest {

    @InjectMocks
    private CadastraMedicoController controller;

    @Mock
    private CadastraMedicoService service;

    @Spy
    private MedicoRequestValidator validator;

    private static final Faker FAKER = FakerFactory.getInstance();

    @Test
    void testCadastraMedicoComSucessoRetornaStatus202Accepted(){
        MedicoRequestModel requestModel = MedicoFactory.getRequestModel();
        Medico model = new Medico(UUID.randomUUID(), requestModel.getNomeMedico(), requestModel.getCrm());

        when(service.cadastraMedico(any(Medico.class))).thenReturn(model);

        ResponseEntity<MedicoResponseModel> response = controller.cadastraMedico(requestModel);

        verify(validator, times(1)).validate(requestModel);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isNotNull()
                .hasNoNullFieldsOrProperties()
                .extracting(MedicoResponseModel::getNomeMedico,
                            MedicoResponseModel::getCrm)
                .containsExactly(requestModel.getNomeMedico(),
                                 requestModel.getCrm());
    }

    @Test
    void testCadastraMedicoSemCrmRetornaBadRequest(){
        MedicoRequestModel requestModel = MedicoFactory.getRequestModel();
        requestModel.setCrm(null);

        BadRequestException bre = Assertions.assertThrows(BadRequestException.class,
                () -> controller.cadastraMedico(requestModel));

        verify(validator, times(1)).validate(requestModel);
        verify(service, never()).cadastraMedico(any(Medico.class));

        Error error = bre.getValidationResult().getErrors().stream().findFirst().get();
        assertThat(error.getField()).isEqualTo(MedicoRequestModel.CRM);
        assertThat(error.getMessage()).isEqualTo(String.format(MedicoRequestValidator.MSG_NULO_OU_VAZIO, MedicoRequestModel.CRM));
    }
}
