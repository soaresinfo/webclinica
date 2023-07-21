package com.soares.webclinica.service.model;

import java.util.UUID;

public record Medico(UUID idMedico,
                     String nomeMedico,
                     String crm) {
}
