package com.soares.webclinica.service.model;

import java.util.UUID;

public record Convenio(UUID idConvenio,
                       String nomeConvenio,
                       String codigoConvenio,
                       Medico medico) {
}
