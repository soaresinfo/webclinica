package com.soares.webclinica.controller.validator;

import br.com.fluentvalidator.AbstractValidator;
import com.soares.webclinica.controller.model.MedicoRequestModel;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThan;
import static com.soares.webclinica.controller.model.MedicoRequestModel.CRM;
import static com.soares.webclinica.controller.model.MedicoRequestModel.NOME_MEDICO;
import static java.util.function.Predicate.not;

@Component
public class MedicoRequestValidator extends AbstractValidator<MedicoRequestModel> {

    public static final String MSG_NULO_OU_VAZIO = "O campo '%s' não pode ser nulo ou vazio";
    public static final String MSG_NAO_MAIOR_QUE = "O campo '%s' não pode ser maior que %s";
    public static final int TAMANHO_NOME_MEDICO = 50;
    public static final int TAMANHO_CRM = 5;

    @Override
    public void rules() {
        ruleFor(request -> request)
                .must(not(stringEmptyOrNull(MedicoRequestModel::getNomeMedico)))
                .withAttempedValue(MedicoRequestModel::getNomeMedico)
                .withFieldName(NOME_MEDICO)
                .withMessage(String.format(MSG_NULO_OU_VAZIO, NOME_MEDICO))

                .must(not(stringSizeGreaterThan(MedicoRequestModel::getNomeMedico, TAMANHO_NOME_MEDICO)))
                .withAttempedValue(MedicoRequestModel::getNomeMedico)
                .withFieldName(NOME_MEDICO)
                .withMessage(String.format(MSG_NAO_MAIOR_QUE, NOME_MEDICO, TAMANHO_NOME_MEDICO))

                .must(not(stringEmptyOrNull(MedicoRequestModel::getCrm)))
                .withAttempedValue(MedicoRequestModel::getCrm)
                .withFieldName(CRM)
                .withMessage(String.format(MSG_NULO_OU_VAZIO, CRM, TAMANHO_CRM))

                .must(not(stringSizeGreaterThan(MedicoRequestModel::getCrm, TAMANHO_CRM)))
                .withAttempedValue(MedicoRequestModel::getCrm)
                .withFieldName(CRM)
                .withMessage(String.format(MSG_NAO_MAIOR_QUE, CRM, TAMANHO_CRM));
    }
}
