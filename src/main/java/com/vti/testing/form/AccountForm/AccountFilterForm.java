package com.vti.testing.form.AccountForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Integer minId;
    private Integer maxId;
}
