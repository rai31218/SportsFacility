package com.sports.facility.utility;

import java.util.Arrays;
import java.util.StringJoiner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	@Override
	public void initialize(ValidPassword arg0) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		PasswordValidator validator = new PasswordValidator(Arrays.asList(new LengthRule(8, 1000),
				new UppercaseCharacterRule(1), new DigitCharacterRule(1), new SpecialCharacterRule(1)

		));

		RuleResult result = validator.validate(new PasswordData(password));
		if (result.isValid()) {
			return true;
		}
		StringJoiner sj2 = new StringJoiner(",");
		for (String res : validator.getMessages(result)) {
			sj2.add(res);
		}
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(sj2.toString()).addConstraintViolation();
		return false;
	}
}