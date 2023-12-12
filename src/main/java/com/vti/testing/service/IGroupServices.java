package com.vti.testing.service;

import com.vti.testing.entity.Group;
import com.vti.testing.form.GroupForm.CreatingGroupForm;
import com.vti.testing.form.GroupForm.GroupFilterForm;
import com.vti.testing.form.GroupForm.UpdateGroupForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGroupServices {

    Page<Group> getAllGroups(Pageable pageable, GroupFilterForm form);
    void createGroup(CreatingGroupForm form);
    Group getGroupById(int id);
    void updateGroup(UpdateGroupForm form);
    void deleteGroupById(int id);
}
