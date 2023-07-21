package com.soares.webclinica.service;

import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.repository.PacienteRepository;
import com.soares.webclinica.repository.model.PacienteEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraPacienteServiceTest {

    @InjectMocks
    private CadastraPacienteService service;

    @Mock
    private PacienteRepository repository;

    @Test
    void testCadastraPacienteRetornaSucesso(){
        Paciente model = new Paciente(null, "Marcelo", LocalDate.now(), "123", null);
        PacienteEntity savedEntity = PacienteMapper.INSTANCE.fromModelToEntity(model);
        savedEntity.setIdPaciente(UUID.randomUUID());

        when(repository.findByCpf(eq(model.cpf()))).thenReturn(Optional.empty());
        when(repository.save(any(PacienteEntity.class))).thenReturn(savedEntity);
        Paciente responseModel = service.cadastraPaciente(model);

        verify(repository, times(1)).findByCpf(anyString());
        verify(repository, times(1)).save(any(PacienteEntity.class));

        assertThat(responseModel).isNotNull();
        assertThat(responseModel.idPaciente()).isNotNull();
        assertThat(responseModel.nomePaciente()).isEqualTo(model.nomePaciente());
        assertThat(responseModel.cpf()).isEqualTo(model.cpf());
        assertThat(responseModel.dataNascimento()).isEqualTo(model.dataNascimento());

    }

    @Test
    void testCadastraPacienteJaExistenteDisparaErro(){
        Paciente model = new Paciente(null, "Marcelo", LocalDate.now(), "123", null);
        PacienteEntity entity = PacienteMapper.INSTANCE.fromModelToEntity(model);
        entity.setIdPaciente(UUID.randomUUID());

        when(repository.findByCpf(eq(model.cpf()))).thenReturn(Optional.of(entity));

        Assertions.assertThrows(UnprocessableEntityException.class,
                () -> service.cadastraPaciente(model));

        verify(repository, times(1)).findByCpf(anyString());
        verify(repository, never()).save(any(PacienteEntity.class));
    }
}
