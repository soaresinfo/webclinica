package com.soares.webclinica.service;

import com.soares.webclinica.mapper.MedicoMapper;
import com.soares.webclinica.repository.MedicoRepository;
import com.soares.webclinica.repository.model.MedicoEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Medico;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraMedicoServiceTest {

    @InjectMocks
    private CadastraMedicoService service;

    @Mock
    private MedicoRepository repository;

    @Test
    void testCadastraMedicoComSucesso(){
        Medico medico = new Medico(null, "Dr. Bento", "123");
        MedicoEntity entitySaved = MedicoMapper.INSTANCE.fromModelToEntity(medico);
        entitySaved.setIdMedico(UUID.randomUUID());

        when(repository.save(any(MedicoEntity.class))).thenReturn(entitySaved);

        Medico response = service.cadastraMedico(medico);

        verify(repository, times(1)).findByCrm(eq(medico.crm()));
        verify(repository, times(1)).save(any());

        assertThat(response).isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void testCadastraMedicoJaExisteDisparaUnprocessableEntity(){
        Medico medico = new Medico(null, "Dr. Bento", "123");

        when(repository.findByCrm(eq(medico.crm()))).thenReturn(Optional.of(MedicoMapper.INSTANCE.fromModelToEntity(medico)));

        assertThrows(UnprocessableEntityException.class,
                () -> service.cadastraMedico(medico));

        verify(repository, never()).save(any(MedicoEntity.class));
    }
}
