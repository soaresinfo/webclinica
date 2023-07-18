package com.soares.webclinica.service.model;

import java.util.UUID;

public record Contato(UUID idContato,
                      String contato,
                      TipoContato tipoContato,
                      Paciente paciente) {
}
