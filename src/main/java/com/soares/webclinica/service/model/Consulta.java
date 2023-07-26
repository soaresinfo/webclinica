package com.soares.webclinica.service.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Consulta(UUID idConsulta,
                       Paciente paciente,
                       Medico medico,
                       LocalDateTime dataConsulta,
                       LocalDateTime dataRetorno,
                       Convenio convenio) {
}
