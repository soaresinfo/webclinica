package com.soares.webclinica.service;

import com.soares.webclinica.repository.PacienteRepository;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.repository.model.ResponsavelEntity;
import com.soares.webclinica.service.exception.NotFoundException;
import com.soares.webclinica.service.model.Paciente;
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

    @Test
    void testBuscaPacientePorIdDisparaNotFoundException(){

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class,
                () -> service.buscaPacientePorId(UUID.randomUUID()));

        verify(repository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testBuscaPacientePorIdRetornaPaciente(){
        PacienteEntity entity = PacienteEntity.builder()
                .idPaciente(UUID.randomUUID())
                .nomePaciente("Marcelo")
                .cpf("12345678900")
                .dataNascimento(LocalDate.now())
                .responsavel(ResponsavelEntity.builder().build())
                .build();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(entity));

        Paciente paciente = service.buscaPacientePorId(UUID.randomUUID());

        verify(repository, times(1)).findById(any(UUID.class));

        assertThat(paciente).isNotNull();
        assertThat(paciente.idPaciente()).isEqualTo(entity.getIdPaciente());
        assertThat(paciente.nomePaciente()).isEqualTo(entity.getNomePaciente());
        assertThat(paciente.cpf()).isEqualTo(entity.getCpf());
        assertThat(paciente.dataNascimento()).isEqualTo(entity.getDataNascimento());
    }
}
