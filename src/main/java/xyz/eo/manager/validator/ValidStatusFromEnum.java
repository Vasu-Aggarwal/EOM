package xyz.eo.manager.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

/**
 * Custom annotation to valid the status passed in the request body. <br/>
 * <code>enumClass</code> is Mandatory.
 */
@Documented
@Constraint(validatedBy = EnumStatusValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatusFromEnum {
    String message() default "Invalid status code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();
}

