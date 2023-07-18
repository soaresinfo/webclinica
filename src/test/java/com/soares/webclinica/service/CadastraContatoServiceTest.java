package com.soares.webclinica.service;

import com.github.javafaker.Faker;
import com.soares.webclinica.controller.exception.BadRequestException;
import com.soares.webclinica.mapper.ContatoMappers;
import com.soares.webclinica.repository.ContatoRepository;
import com.soares.webclinica.repository.model.ContatoEntity;
import com.soares.webclinica.service.model.Contato;
import com.soares.webclinica.service.model.Paciente;
import com.soares.webclinica.service.model.Responsavel;
import com.soares.webclinica.service.model.TipoContato;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CadastraContatoServiceTest {

    @InjectMocks
    private CadastraContatoService service;

    @Mock
    private ContatoRepository repository;

    private static final Faker FAKER = Faker.instance(new Locale("pt-BR"));

    @Test
    void testCadastraContatoSucesso(){
        Contato contato = getContato();
        ContatoEntity entity = ContatoMappers.INSTANCE.fromModelToEntity(contato);
        entity.setIdContato(UUID.randomUUID());

        when(repository.save(any(ContatoEntity.class))).thenReturn(entity);

        Contato response = service.cadastraContato(contato);

        verify(repository, times(1)).save(any(ContatoEntity.class));

        assertThat(response).isNotNull().hasNoNullFieldsOrProperties()
                .extracting(Contato::idContato,
                            Contato::contato)
                .containsExactly(entity.getIdContato(),
                                 entity.getContato());
    }

    @Test
    void testCadastraContatoJaExistenteRetornaErro(){
        Contato contato = getContato();
        ContatoEntity entity = ContatoMappers.INSTANCE.fromModelToEntity(contato);
        entity.setIdContato(UUID.randomUUID());

        when(repository.findByContato(eq(contato.contato()))).thenReturn(Optional.of(entity));

        Assertions.assertThrows(BadRequestException.class,
                () -> service.cadastraContato(contato));

        verify(repository, times(1)).findByContato(anyString());
        verify(repository, never()).save(any(ContatoEntity.class));
    }


    private static Contato getContato() {
        return new Contato(null, FAKER.phoneNumber().cellPhone(),
                            new TipoContato("A", "Celular/WhatsApp"),
                            new Paciente(UUID.randomUUID(),FAKER.name().fullName(), LocalDate.now(),FAKER.numerify("###########"),
                                    new Responsavel(UUID.randomUUID(), FAKER.name().fullName(),LocalDate.now(), FAKER.numerify("###########"))));
    }
}
