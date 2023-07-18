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
public class ResponsavelResponseModel {

    public static final String NOME_RESPONSAVEL = "nome_responsavel";
    public static final String DATA_NASCIMENTO = "data_nascimento";
    public static final String CPF = "cpf";

    @JsonProperty(value = NOME_RESPONSAVEL)
    private String nomeResponsavel;

    @JsonProperty(value = DATA_NASCIMENTO)
    private String dataNascimento;

    @JsonProperty(value = CPF)
    private String cpf;
}
