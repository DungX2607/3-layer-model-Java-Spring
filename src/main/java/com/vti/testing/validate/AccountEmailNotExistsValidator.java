package com.vti.testing.validate;

import com.vti.testing.respository.IAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class AccountEmailNotExistsValidator implements ConstraintValidator<AccountEmailNotExists, String> {
    @Autowired
    private IAccountRespository accountRespository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRespository.existsByEmail(email);
    }
}
