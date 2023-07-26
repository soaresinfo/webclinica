package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import com.github.javafaker.Faker;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.repository.MedicoRepository;
import com.soares.webclinica.repository.model.MedicoEntity;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Medico;
import com.soares.webclinica.util.FakerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscaMedicoServiceTest {

    @InjectMocks
    private BuscaMedicoService service;

    @Mock
    private MedicoRepository repository;

    private static final Faker FAKER = FakerFactory.getInstance();

    @Test
    void testBuscaMedicoPorNomeSucesso(){
        String nomeMedico = FAKER.name().fullName();
        MedicoEntity medico = MedicoEntity.builder()
                .idMedico(UUID.randomUUID())
                .nomeMedico(nomeMedico)
                .crm(FAKER.numerify("#####"))
                .build();

        when(repository.findByNomeMedico(eq(nomeMedico))).thenReturn(Optional.of(medico));

        Medico response = service.buscaPorNome(nomeMedico);

        verify(repository, times(1)).findByNomeMedico(eq(nomeMedico));

        assertThat(response).isNotNull()
                .hasNoNullFieldsOrProperties()
                .extracting(Medico::nomeMedico)
                .isEqualTo(nomeMedico);
    }

    @Test
    void testBuscaMedicoPorNomeDisparaExcecaoNotFound(){
        String nomeMedico = FAKER.name().fullName();

        when(repository.findByNomeMedico(eq(nomeMedico))).thenReturn(Optional.empty());

        NotFoundException nfe = assertThrows(NotFoundException.class,
                () -> service.buscaPorNome(nomeMedico));

        verify(repository, times(1)).findByNomeMedico(eq(nomeMedico));

        assertThat(nfe.getValidationResult()).isNotNull();
        assertThat(nfe.getValidationResult().getErrors()).isNotEmpty();

        Error error = nfe.getValidationResult().getErrors().stream().findFirst().get();
        assertThat(error.getField()).isEqualTo(MedicoRequestModel.NOME_MEDICO);
        assertThat(error.getAttemptedValue()).isEqualTo(nomeMedico);
    }

    @Test
    void testBuscaMedicoPorNomeDisparaExcecaoRuntime(){
        String nomeMedico = FAKER.name().fullName();

        when(repository.findByNomeMedico(eq(nomeMedico))).thenThrow(RuntimeException.class);

        assertThrows(RuntimeException.class,
                () -> service.buscaPorNome(nomeMedico));
    }

    @Test
    void testBuscaTodosMedicosRetornaLista(){
        when(repository.findAll()).thenReturn(Arrays.asList(getMedicoEntity(), getMedicoEntity()));

        List<Medico> listResponse = service.buscaTodos();

        assertThat(listResponse).isNotNull()
                .isNotEmpty()
                .hasSize(2);
    }

    @Test
    void testBuscaTodosMedicosRetornaListaVazia(){

        when(repository.findAll()).thenReturn(new ArrayList<>());

        List<Medico> listResponse = service.buscaTodos();

        assertThat(listResponse).isNotNull().isEmpty();
    }

    private static MedicoEntity getMedicoEntity() {
        return MedicoEntity.builder()
                .idMedico(UUID.randomUUID())
                .nomeMedico(FAKER.name().fullName())
                .crm(FAKER.numerify("#####"))
                .build();
    }
}
