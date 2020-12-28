package com.kpi.internetshop.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Password length - from 8 to 20." +
            " A digit, lower and upper case letter must occur at least once." +
            " No whitespace allowed. Try again! ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
