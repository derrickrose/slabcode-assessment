package com.slabcode.assessment.entity.validation;

import com.slabcode.assessment.entity.Task;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskValidation
        implements ConstraintValidator<ITaskValidator, Task> {

    @Override
    public void initialize(ITaskValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(Task task, ConstraintValidatorContext context) {
        if (task == null) {
            return true;
        }

        return task.getDate1().before(task.getDate2());
    }
}