package com.vti.testing.validate;

import com.vti.testing.respository.IAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountIdNotExistxValidator implements ConstraintValidator<AccountIdNotExists, Integer> {
    @Autowired
    private IAccountRespository accountRespository;
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return accountRespository.existsById(id);
    }
}
