package com.soares.webclinica.factory;

import com.github.javafaker.Faker;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import com.soares.webclinica.service.model.Medico;
import com.soares.webclinica.util.FakerFactory;

import java.util.UUID;

public class MedicoFactory {

    private static final Faker FAKER = FakerFactory.getInstance();

    public static Medico getModel(){
        return new Medico(UUID.randomUUID(), FAKER.name().fullName(), FAKER.numerify("#####"));
    }

    public static MedicoRequestModel getRequestModel(){
        return MedicoRequestModel.builder()
                .nomeMedico(FAKER.name().fullName())
                .crm(FAKER.numerify("#####"))
                .build();
    }
}
