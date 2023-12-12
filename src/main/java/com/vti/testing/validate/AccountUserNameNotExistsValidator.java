package com.vti.testing.validate;

import com.vti.testing.respository.IAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountUserNameNotExistsValidator implements ConstraintValidator<AccountUserNameNotExist, String> {

    @Autowired
    private IAccountRespository accountRespository;
    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return !accountRespository.existsByUserName(userName);
    }
}
