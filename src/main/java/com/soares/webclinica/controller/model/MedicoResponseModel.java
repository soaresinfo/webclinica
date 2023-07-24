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
public class MedicoResponseModel {

    public static final String ID_MEDICO = "id_medico";

    public static final String NOME_MEDICO = "nome_medico";

    public static final String CRM = "crm";

    @JsonProperty(value = ID_MEDICO)
    private String idMedico;

    @JsonProperty(value = NOME_MEDICO)
    private String nomeMedico;

    @JsonProperty(value = CRM)
    private String crm;
}
