package com.vti.testing.form.GroupForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class GroupFilterForm {
    private String search;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;
    private int minYear;
}
