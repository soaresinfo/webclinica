package com.soares.webclinica.util;

import com.github.javafaker.Faker;

import java.util.Locale;

public class FakerFactory {

    private static final Faker INSTANCE = Faker.instance(new Locale("pt-BR"));

    public static Faker getInstance(){
        return INSTANCE;
    }

}
