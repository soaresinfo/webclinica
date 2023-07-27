package com.soares.webclinica.service;

import com.soares.webclinica.factory.ConsultaFactory;
import com.soares.webclinica.mapper.ConsultaMapper;
import com.soares.webclinica.repository.ConsultaRepository;
import com.soares.webclinica.repository.model.ConsultaEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Consulta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraConsultaServiceTest {

    @InjectMocks
    private CadastraConsultaService service;

    @Mock
    private ConsultaRepository repository;

    @Mock
    private NotificacaoService notificacaoService;

    @Test
    void testCadastraConsultaSucessoRetornaConsulta(){
        Consulta consulta = ConsultaFactory.getModel();
        ConsultaEntity entity = ConsultaMapper.INSTANCE.fromModelToEntity(consulta);

        ConsultaEntity entityExample = ConsultaEntity.builder()
                .dataConsulta(entity.getDataConsulta())
                .medico(entity.getMedico())
                .build();

        Example<ConsultaEntity> example = Example.of(entityExample);

        when(repository.findOne(eq(example))).thenReturn(Optional.empty());
        when(repository.save(entity)).thenReturn(entity);

        Consulta response = service.cadastraConsulta(consulta);

        verify(repository, times(1)).findOne(any(example.getClass()));
        verify(repository, times(1)).save(eq(entity));
        verify(notificacaoService, times(1)).enviaNotificacao(eq(consulta.paciente()));

        assertThat(response).isNotNull();
    }

    @Test
    void testCadastraConsultaHorarioJaMarcadoRetornaUnprocessableEntity(){
        Consulta consulta = ConsultaFactory.getModel();
        ConsultaEntity entity = ConsultaMapper.INSTANCE.fromModelToEntity(consulta);

        ConsultaEntity entityExample = ConsultaEntity.builder()
                .dataConsulta(entity.getDataConsulta())
                .medico(entity.getMedico())
                .build();

        Example<ConsultaEntity> example = Example.of(entityExample);

        when(repository.findOne(eq(example))).thenReturn(Optional.of(entityExample));

        assertThrows(UnprocessableEntityException.class,
                () -> service.cadastraConsulta(consulta));

        verify(repository, times(1)).findOne(any(example.getClass()));
        verify(repository, never()).save(eq(entity));
        verify(notificacaoService, never()).enviaNotificacao(eq(consulta.paciente()));
    }
}
