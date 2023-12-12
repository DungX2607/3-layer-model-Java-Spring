package com.vti.testing.controller;


import com.vti.testing.dto.GroupDTO;
import com.vti.testing.entity.Group;
import com.vti.testing.form.GroupForm.CreatingGroupForm;
import com.vti.testing.form.GroupForm.GroupFilterForm;
import com.vti.testing.form.GroupForm.UpdateGroupForm;
import com.vti.testing.service.IGroupServices;
import com.vti.testing.validate.GroupIdNotExists;
import com.vti.testing.validate.GroupNameNotExists;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/groups", produces = "application/json")
@ResponseBody
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
@Validated
public class GrouptController {
    @Autowired
    private IGroupServices groupServices;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<GroupDTO> getAllGroup(Pageable pageable, GroupFilterForm form) {
        Page<Group> groupPage = groupServices.getAllGroups(pageable, form);
        List<Group> groups = groupPage.getContent();
        List<GroupDTO> groupDTOS =
                groups.stream().map(group -> modelMapper.map(group, GroupDTO.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(groupDTOS, pageable, groupPage.getTotalElements());
    }

    @GetMapping("/{id}")
    public GroupDTO getGroupById( @PathVariable("id") @GroupIdNotExists int id) {
        Group group = groupServices.getGroupById(id);
        return modelMapper.map(group, GroupDTO.class);
    }

    @PostMapping
    public ResponseEntity<String> createGroup(@RequestBody @Valid @GroupNameNotExists CreatingGroupForm form) {
//        log.debug("create group"+form);
        groupServices.createGroup(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGroup(@PathVariable @GroupIdNotExists int id, @RequestBody UpdateGroupForm form) {
//        log.debug("update group"+form);
        form.setId(id);
        groupServices.updateGroup(form);
        return  new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable @GroupIdNotExists int id){
        groupServices.deleteGroupById(id);
        return  new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }

}
