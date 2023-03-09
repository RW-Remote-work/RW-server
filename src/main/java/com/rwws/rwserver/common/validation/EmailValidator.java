package com.rwws.rwserver.common.validation;

import com.rwws.rwserver.common.constant.RegexPatternFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;

public class EmailValidator implements ConstraintValidator<EmailCheck, String> {


    @Override
    public void initialize(EmailCheck emailCheck) {
        ConstraintValidator.super.initialize(emailCheck);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = RegexPatternFactory.emailPattern().matcher(email);
        return matcher.find();
    }
}
