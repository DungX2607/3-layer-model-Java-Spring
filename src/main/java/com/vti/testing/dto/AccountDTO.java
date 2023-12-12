package com.vti.testing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private int id;
    private String email;
    private String userName;
    private String fullName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    private List<GrouptDTO> groups;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class GrouptDTO {
        private int groupId;
        private String groupName;

    }

}
