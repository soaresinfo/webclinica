package com.soares.webclinica.service.model;

import java.time.LocalDate;
import java.util.UUID;

public record Paciente(UUID idPaciente,
                       String nomePaciente,
                       LocalDate dataNascimento,
                       String cpf,
                       Responsavel responsavel) {
}
