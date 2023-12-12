package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private int id;
    private String groupName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdDate;

//    private Integer creatorId;
//    private String userName;

 private AccountDto creator;
    @Getter
    @Setter
    @NoArgsConstructor
    public static class AccountDto{
        private int accountId;
        private String userName;
    }
}
