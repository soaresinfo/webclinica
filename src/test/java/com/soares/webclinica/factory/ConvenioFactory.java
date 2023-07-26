package com.soares.webclinica.factory;

import com.github.javafaker.Faker;
import com.soares.webclinica.service.model.Convenio;
import com.soares.webclinica.service.model.Medico;
import com.soares.webclinica.util.FakerFactory;

import java.util.UUID;

public class ConvenioFactory {

    private static final Faker FAKER = FakerFactory.getInstance();

    public static Convenio getModel(){
        return new Convenio(UUID.randomUUID(), FAKER.company().name(), FAKER.numerify("##########"), MedicoFactory.getModel());
    }

    public static Convenio getModel(Medico medico){
        return new Convenio(UUID.randomUUID(), FAKER.company().name(), FAKER.numerify("##########"), medico);
    }
}
