package com.soares.webclinica.controller.validator;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.StringPredicate;
import com.soares.webclinica.controller.model.PacienteRequestModel;
import com.soares.webclinica.mapper.CommonsMapper;

import static br.com.fluentvalidator.predicate.ObjectPredicate.nullValue;
import static br.com.fluentvalidator.predicate.StringPredicate.*;
import static com.soares.webclinica.controller.model.PacienteRequestModel.*;
import static java.util.function.Predicate.not;

public class PacienteRequestValidator extends AbstractValidator<PacienteRequestModel> {
    @Override
    public void rules() {
        ruleFor(request -> request)
                .must(not(stringEmptyOrNull(PacienteRequestModel::getNomePaciente)))
                .withAttempedValue(PacienteRequestModel::getNomePaciente)
                .withFieldName(NOME_PACIENTE)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", NOME_PACIENTE))

                .must(not(StringPredicate.stringSizeGreaterThan(PacienteRequestModel::getNomePaciente, 50)))
                .withAttempedValue(PacienteRequestModel::getNomePaciente)
                .withFieldName(NOME_PACIENTE)
                .withMessage(String.format("O campo '%s' não pode ser maior que 50", NOME_PACIENTE))

                .must(isDate(PacienteRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(PacienteRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", DATA_NASCIMENTO))

                .must(isDate(PacienteRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(PacienteRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format("O campo '%s' deve ser no formato 'yyyy-mm-dd", DATA_NASCIMENTO))

                .must(not(stringEmptyOrNull(PacienteRequestModel::getCpf)))
                .withAttempedValue(PacienteRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", CPF))

                .must(stringSize(PacienteRequestModel::getCpf, 11))
                .withAttempedValue(PacienteRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format("O campo '%s' deve ter exatamente 11 dígitos", CPF))
        ;

        ruleFor(PacienteRequestModel::getResponsavel)
                .whenever(not(nullValue()))
                .withValidator(new ResponsavelRequestValidator());
    }
}
