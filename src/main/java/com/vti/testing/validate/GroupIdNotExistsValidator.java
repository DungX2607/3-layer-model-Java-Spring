package com.vti.testing.validate;

import com.vti.testing.respository.IGrouptRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupIdNotExistsValidator implements ConstraintValidator<GroupIdNotExists, Integer> {
    @Autowired
    private IGrouptRespository grouptRespository;
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return grouptRespository.existsById(id);
    }
}
