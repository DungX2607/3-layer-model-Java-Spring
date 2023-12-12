package com.vti.testing.validate;

import com.vti.testing.respository.IGrouptRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GroupNameNotExistsValidator implements ConstraintValidator<GroupNameNotExists, String> {
    @Autowired
    private IGrouptRespository grouptRespository;
    @Override
    public boolean isValid(String groupName, ConstraintValidatorContext constraintValidatorContext) {
        return !grouptRespository.existsByGroupName(groupName);
    }
}
