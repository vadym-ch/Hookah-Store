package com.kpi.internetshop.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.length() > 8
                && email.length() < 50
                && org.apache.commons.validator.routines.EmailValidator
                .getInstance().isValid(email);
    }
}
