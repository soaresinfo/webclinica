package com.soares.webclinica.controller.validator;

import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static java.util.function.Function.identity;

import java.util.Objects;
import java.util.function.Function;

import br.com.fluentvalidator.AbstractValidator;
import br.com.fluentvalidator.predicate.StringPredicate;

public class UUIDValidator extends AbstractValidator<String> {

	private final Function<Object, String> composeFieldName;

	private UUIDValidator(final Function<Object, String> composeFieldName) {
		this.composeFieldName = Objects.requireNonNull(composeFieldName, "Function must not be null");
	}

	public UUIDValidator(final String fieldName) {
		this(fn -> fieldName);
	}

	// @formater:off
	@Override
	public void rules() {
		ruleFor(identity())
			.must(not(stringEmptyOrNull()))
				.withMessage(fn -> String.format("Campo '%s' não pode ser nulo ou vazio.", composeFieldName.apply(fn)))
				.withFieldName(composeFieldName::apply)
				.withAttempedValue(value -> value)
			.must(StringPredicate.stringMatches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$")).when(not(stringEmptyOrNull()))
				.withMessage(fn -> String.format("Campo '%s' deve estar no formato UUID.", composeFieldName.apply(fn)))
				.withFieldName(composeFieldName::apply)
				.withAttempedValue(value -> value);

	}
	// @formater:on

}
