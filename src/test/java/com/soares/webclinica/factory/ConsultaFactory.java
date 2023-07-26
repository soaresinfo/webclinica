package com.soares.webclinica.factory;

import com.github.javafaker.Faker;
import com.soares.webclinica.service.model.Consulta;
import com.soares.webclinica.service.model.Medico;
import com.soares.webclinica.util.FakerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

public class ConsultaFactory {

    private static final Faker FAKER = FakerFactory.getInstance();

    public static Consulta getModel(){
        Medico medico = MedicoFactory.getModel();
        return new Consulta(UUID.randomUUID(), PacienteFactory.getModel(), medico, LocalDateTime.now(), null, ConvenioFactory.getModel(medico));
    }


}
