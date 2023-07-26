package com.soares.webclinica.service;

import com.soares.webclinica.mapper.PacienteMapper;
import com.soares.webclinica.repository.ContatoRepository;
import com.soares.webclinica.repository.model.ContatoEntity;
import com.soares.webclinica.service.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificacaoService {

    private final ContatoRepository repository;

    public boolean enviaNotificacao(Paciente paciente) {
        List<ContatoEntity> list = repository.findByPaciente(PacienteMapper.INSTANCE.fromModelToEntity(paciente));

        list.stream().forEach(contatoEntity -> notifica(contatoEntity));
        return true;
    }

    private void notifica(ContatoEntity contatoEntity) {
    }
}
