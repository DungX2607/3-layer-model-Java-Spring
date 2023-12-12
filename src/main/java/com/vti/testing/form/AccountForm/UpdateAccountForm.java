package com.vti.testing.form.AccountForm;

import com.vti.testing.validate.AccountEmailNotExists;
import com.vti.testing.validate.AccountUserNameNotExist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class UpdateAccountForm {

    private int id;
    @Length(max = 50,message = "max length 50")
    @AccountUserNameNotExist
    private String userName;
    @Length(max = 50,message = "max length 50")
    @AccountEmailNotExists
    private String email;
    @Length(max = 50,message = "max length 50")
    private String firstName;
    @Length(max = 50,message = "max length 50")
    private String lastName;
    @Length(max = 50,message = "max length 50")
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
}
