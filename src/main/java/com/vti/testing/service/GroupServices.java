package com.vti.testing.service;

import com.vti.testing.entity.Group;
import com.vti.testing.form.GroupForm.CreatingGroupForm;
import com.vti.testing.form.GroupForm.GroupFilterForm;
import com.vti.testing.form.GroupForm.UpdateGroupForm;
import com.vti.testing.respository.IAccountRespository;
import com.vti.testing.respository.IGrouptRespository;
import com.vti.testing.specification.GroupSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupServices implements IGroupServices {
    @Autowired
    private IGrouptRespository grouptRespository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IAccountRespository accountRespository;

    @Override
    public Page<Group> getAllGroups(Pageable pageable, GroupFilterForm form) {
        Specification<Group> where = GroupSpecification.buildWhere(form);
        return grouptRespository.findAll(where ,pageable);
    }

    @Override
    public void createGroup(CreatingGroupForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(CreatingGroupForm.class, Group.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingGroupForm, Group>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }
        Group group = modelMapper.map(form, Group.class);
        grouptRespository.save(group);

    }

    @Override
    public Group getGroupById(int id) {
        return grouptRespository.findById(id).get();
    }

    @Override
    public void updateGroup(UpdateGroupForm form) {
        Group group= modelMapper.map(form, Group.class);
        grouptRespository.save(group);
    }

    @Override
    public void deleteGroupById(int id) {
        grouptRespository.deleteById(id);
    }
}
