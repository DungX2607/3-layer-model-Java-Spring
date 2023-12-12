package com.vti.testing.form.GroupForm;

import com.vti.testing.validate.GroupNameNotExists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class CreatingGroupForm {

    @Length(max = 50, message = "group's length must less than or equal to 50")
    @NotBlank(message = "group must not null")
    @GroupNameNotExists
    private String groupName;
    private Integer creatorId;

}
