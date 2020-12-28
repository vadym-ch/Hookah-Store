package com.kpi.internetshop.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "Email does not match to the standard pattern. Try again!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
