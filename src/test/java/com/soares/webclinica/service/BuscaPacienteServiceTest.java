package com.soares.webclinica.service;

import com.github.javafaker.Faker;
import com.soares.webclinica.repository.PacienteRepository;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.repository.model.ResponsavelEntity;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
import com.soares.webclinica.util.FakerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscaPacienteServiceTest {

    @InjectMocks
    private BuscaPacienteService service;

    @Mock
    private PacienteRepository repository;

    private static final Faker FAKER = FakerFactory.getInstance();

    @Test
    void testBuscaPacientePorIdDisparaNotFoundException(){

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class,
                () -> service.buscaPacientePorId(UUID.randomUUID()));

        verify(repository, only()).findById(any(UUID.class));
    }

    @Test
    void testBuscaPacientePorIdRetornaPaciente(){
        PacienteEntity entity = getPacienteEntity();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(entity));

        Paciente paciente = service.buscaPacientePorId(UUID.randomUUID());

        verify(repository, only()).findById(any(UUID.class));

        assertThat(paciente).isNotNull();
        assertThat(paciente.idPaciente()).isEqualTo(entity.getIdPaciente());
        assertThat(paciente.nomePaciente()).isEqualTo(entity.getNomePaciente());
        assertThat(paciente.cpf()).isEqualTo(entity.getCpf());
        assertThat(paciente.dataNascimento()).isEqualTo(entity.getDataNascimento());
    }

    private static PacienteEntity getPacienteEntity() {
        return PacienteEntity.builder()
                .idPaciente(UUID.randomUUID())
                .nomePaciente(FAKER.name().fullName())
                .cpf(FAKER.numerify("###########"))
                .dataNascimento(LocalDate.now())
                .responsavel(ResponsavelEntity.builder().build())
                .build();
    }

    @Test
    void testBuscaPacientePorNomeRetornaPaciente(){
        PacienteEntity entity =  getPacienteEntity();
        String nomePaciente = entity.getNomePaciente();
        when(repository.findByNomePaciente(eq(nomePaciente))).thenReturn(Optional.of(entity));

        Paciente response = service.buscaPacientePorNome(nomePaciente);

        assertThat(response).isNotNull()
                .hasNoNullFieldsOrProperties();

        verify(repository, only()).findByNomePaciente(eq(nomePaciente));

    }

    @Test
    void testBuscaPacientePorNomeDisparaNotFound(){
        String nomePaciente = FAKER.name().fullName();
        when(repository.findByNomePaciente(eq(nomePaciente))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> service.buscaPacientePorNome(nomePaciente));

        verify(repository, only()).findByNomePaciente(eq(nomePaciente));

    }
}
