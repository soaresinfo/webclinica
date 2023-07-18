package com.soares.webclinica.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PacienteRequestModel {

    public static final String NOME_PACIENTE = "nome_paciente";
    public static final String DATA_NASCIMENTO = "data_nascimento";
    public static final String CPF = "cpf";
    public static final String RESPONSAVEL = "responsavel";

    @JsonProperty(value = NOME_PACIENTE)
    private String nomePaciente;

    @JsonProperty(value = DATA_NASCIMENTO)
    private String dataNascimento;

    @JsonProperty(value = CPF)
    private String cpf;

    @JsonProperty(value = RESPONSAVEL)
    private ResponsavelRequestModel responsavel;
}
