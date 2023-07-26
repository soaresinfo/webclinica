package com.soares.webclinica.service;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.soares.webclinica.mapper.ConsultaMapper;
import com.soares.webclinica.repository.ConsultaRepository;
import com.soares.webclinica.repository.model.ConsultaEntity;
import com.soares.webclinica.service.exception.UnprocessableEntityException;
import com.soares.webclinica.service.model.Consulta;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastraConsultaService {

    private final ConsultaRepository repository;

    private final NotificacaoService notificacaoService;

    public Consulta cadastraConsulta(Consulta consulta) {

        ConsultaEntity entity = ConsultaMapper.INSTANCE.fromModelToEntity(consulta);

        ConsultaEntity entityExample = ConsultaEntity.builder()
                .dataConsulta(entity.getDataConsulta())
                .medico(entity.getMedico())
                .build();

        Example<ConsultaEntity> example = Example.of(entityExample);

        if (repository.findOne(example).isEmpty()) {
            Consulta responseModel = ConsultaMapper.INSTANCE.fromEntityToModel(repository.save(entity));
            notificacaoService.enviaNotificacao(consulta.paciente());
            return responseModel;
        }

        ValidationResult validation = ValidationResult.fail(List.of(Error.create("data_consulta","Horário de consulta indisponível",
                String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), consulta.dataConsulta())));
        throw new UnprocessableEntityException(validation);
    }
}
