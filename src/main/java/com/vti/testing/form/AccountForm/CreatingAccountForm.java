package com.vti.testing.form.AccountForm;

import com.vti.testing.validate.AccountEmailNotExists;
import com.vti.testing.validate.AccountUserNameNotExist;
import com.vti.testing.validate.GroupNameNotExists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreatingAccountForm {
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "username not null")
    @AccountUserNameNotExist
    private String userName;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "email not null")
    @AccountEmailNotExists
    private String email;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "firstName not null")
    private String firstName;
    @Length(max = 50, message = "max length 50")
    @NotBlank(message = "lastName not null")
    private String lastName;
    @Length(max = 250, message = "max length 50")
    @NotBlank(message = "password not null")
    private String password;

    private List<Group> groups;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Group {
        @Length(max = 50, message = "group's length must less than or equal to 50")
        @NotBlank(message = "group must not null")
        @GroupNameNotExists
        private String groupName;
        private Date createdDate;


    }

}
