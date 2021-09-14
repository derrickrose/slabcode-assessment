package com.slabcode.assessment.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {TaskValidation.class})
@Documented
public @interface ITaskValidator {

    String message() default "date2 should be greater than date1";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

