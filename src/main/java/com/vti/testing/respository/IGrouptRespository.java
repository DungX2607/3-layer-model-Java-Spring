package com.vti.testing.respository;


import com.vti.testing.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IGrouptRespository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
    boolean existsByGroupName(String groupName);

}
