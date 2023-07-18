package com.soares.webclinica.controller.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.soares.webclinica.controller.model.ResponsavelRequestModel;
import com.soares.webclinica.mapper.CommonsMapper;

import static br.com.fluentvalidator.predicate.StringPredicate.*;
import static com.soares.webclinica.controller.model.PacienteRequestModel.CPF;
import static com.soares.webclinica.controller.model.PacienteRequestModel.DATA_NASCIMENTO;
import static com.soares.webclinica.controller.model.ResponsavelRequestModel.NOME_RESPONSAVEL;
import static java.util.function.Predicate.not;

public class ResponsavelRequestValidator extends AbstractValidator<ResponsavelRequestModel> {
    @Override
    public void rules() {
        ruleFor(request -> request)
                .must(not(stringEmptyOrNull(ResponsavelRequestModel::getNomeResponsavel)))
                .withAttempedValue(ResponsavelRequestModel::getNomeResponsavel)
                .withFieldName(NOME_RESPONSAVEL)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", NOME_RESPONSAVEL))

                .must(not(stringSizeGreaterThan(ResponsavelRequestModel::getNomeResponsavel, 50)))
                .withAttempedValue(ResponsavelRequestModel::getNomeResponsavel)
                .withFieldName(NOME_RESPONSAVEL)
                .withMessage(String.format("O campo '%s' não pode ser maior que 50", NOME_RESPONSAVEL))

                .must(isDate(ResponsavelRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(ResponsavelRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", DATA_NASCIMENTO))

                .must(isDate(ResponsavelRequestModel::getDataNascimento, CommonsMapper.DATE_FORMAT))
                .withAttempedValue(ResponsavelRequestModel::getDataNascimento)
                .withFieldName(DATA_NASCIMENTO)
                .withMessage(String.format("O campo '%s' deve ser no formato 'yyyy-mm-dd", DATA_NASCIMENTO))

                .must(not(stringEmptyOrNull(ResponsavelRequestModel::getCpf)))
                .withAttempedValue(ResponsavelRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format("O campo '%s' não pode ser nulo ou vazio", CPF))

                .must(stringSize(ResponsavelRequestModel::getCpf, 11))
                .withAttempedValue(ResponsavelRequestModel::getCpf)
                .withFieldName(CPF)
                .withMessage(String.format("O campo '%s' deve ter exatamente 11 dígitos", CPF));

    }
}
