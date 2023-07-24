package com.soares.webclinica.controller.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.soares.webclinica.controller.model.PacienteRequestModel;
import com.soares.webclinica.mapper.CommonsMapper;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.*;
import static com.soares.webclinica.controller.model.PacienteRequestModel.*;
import static java.util.function.Predicate.not;

@Component
public class PacienteRequestValidator extends AbstractValidator<PacienteRequestModel> {

    public static final String MSG_NULO_OU_VAZIO = "O campo '%s' não pode ser nulo ou vazio";
    public static final String MSG_NAO_MAIOR_QUE = "O campo '%s' não pode ser maior que %s";
    public static final String MSG_DATA_FORMATO = "O campo '%s' deve ser no formato 'yyyy-mm-dd";
    public static final String MSG_EXATO = "O campo '%s' deve ter exatamente %s dígitos";
    public static final int TAMANHO_NOME_PACIENTE = 50;
    public static final int TAMANHO_CPF = 11;

    @Override
    public void rules() {
        ruleFor(request -> request)
                .must(not(stringEmptyOrNull(PacienteRequestModel::getNomePaciente)))
                .withAttempedValue(PacienteRequestModel::getNomePaciente)
                .withFieldName(NOME_PACIENTE)
                .withMessage(String.format(MSG_NULO_OU_VAZIO, NOME_PACIENTE))

                .must(not(stringSizeGreaterThan(PacienteRequestModel::getNomePaciente, TAMANHO_NOME_PACIENTE)))
                .withAttempedValue(PacienteRequestModel::getNomePaciente)
                .withFieldName(NOME_PACIENTE)
                .withMessage(String.format(MSG_NAO_MAIOR_QUE, NOME_PACIENTE, TAMANHO_NOME_PACIENTE))

                .must(isDate(PacienteRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(PacienteRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format(MSG_NULO_OU_VAZIO, DATA_NASCIMENTO))

                .must(isDate(PacienteRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(PacienteRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format(MSG_DATA_FORMATO, DATA_NASCIMENTO))

                .must(not(stringEmptyOrNull(PacienteRequestModel::getCpf)))
                .withAttempedValue(PacienteRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format(MSG_NULO_OU_VAZIO, CPF))

                .must(stringSize(PacienteRequestModel::getCpf, TAMANHO_CPF))
                .withAttempedValue(PacienteRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format(MSG_EXATO, CPF, TAMANHO_CPF))
        ;

        ruleFor(PacienteRequestModel::getResponsavel)
                .whenever(not(nullValue()))
                .withValidator(new ResponsavelRequestValidator());
    }
}
