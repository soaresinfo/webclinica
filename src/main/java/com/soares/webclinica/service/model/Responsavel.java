package com.soares.webclinica.service.model;

import java.time.LocalDate;
import java.util.UUID;

public record Responsavel(UUID idResponsavel,
                          String nomeResponsavel,
                          LocalDate dataNascimento,
                          String cpf) {
}
