package com.soares.webclinica.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MedicoRequestModel {

    public static final String NOME_MEDICO = "nome_medico";

    public static final String CRM = "crm";

    @JsonProperty(value = NOME_MEDICO)
    private String nomeMedico;

    @JsonProperty(value = CRM)
    private String crm;
}
