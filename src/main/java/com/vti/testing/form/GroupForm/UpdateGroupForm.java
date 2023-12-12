package com.vti.testing.form.GroupForm;

import com.vti.testing.validate.GroupNameNotExists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class UpdateGroupForm {


    private int id;
    @Length(max = 50, message = "group's length must less than or equal to 50")
    @GroupNameNotExists
    private String groupName;
    private Integer creatorId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;



}
