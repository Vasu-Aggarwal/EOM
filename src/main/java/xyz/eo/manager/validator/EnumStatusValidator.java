package xyz.eo.manager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import xyz.eo.manager.util.ErrorMessage;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class EnumStatusValidator implements ConstraintValidator<ValidStatusFromEnum, Integer> {

    private final Set<Integer> allowedValues = new HashSet<>();

    @Override
    public void initialize(ValidStatusFromEnum constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
        try {
            Method getStatusMethod = enumClass.getMethod("getStatus");

            for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
                Object value = getStatusMethod.invoke(enumConstant);
                if (value instanceof Integer) {
                    allowedValues.add((Integer) value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessage.INTERNAL_SERVER_ERROR + enumClass.getName(), e);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && allowedValues.contains(value);
    }
}

