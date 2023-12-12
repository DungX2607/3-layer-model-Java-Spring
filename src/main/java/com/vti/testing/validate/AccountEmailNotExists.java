package com.vti.testing.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(
        {
                ElementType.PARAMETER,
                ElementType.FIELD,
                ElementType.TYPE_USE,
                ElementType.CONSTRUCTOR,
                ElementType.METHOD
        }
)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountEmailNotExistsValidator.class)
public @interface AccountEmailNotExists {
    String message() default "Account email exists";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
