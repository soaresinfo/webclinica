package com.soares.webclinica.factory;

import com.github.javafaker.Faker;
import com.soares.webclinica.service.model.Paciente;
import com.soares.webclinica.util.FakerFactory;

import java.time.LocalDate;
import java.util.UUID;

public class PacienteFactory {

    private static final Faker FAKER = FakerFactory.getInstance();

    public static Paciente getModel(UUID idPaciente) {
        return new Paciente(idPaciente, FAKER.name().fullName(), LocalDate.now(), FAKER.numerify("###########"), null);
    }

    public static Paciente getModel() {
        return new Paciente(UUID.randomUUID(), FAKER.name().fullName(), LocalDate.now(), FAKER.numerify("###########"), null);
    }
}
