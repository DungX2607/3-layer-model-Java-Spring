package com.vti.testing.respository;

import com.vti.testing.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface IAccountRespository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Account findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
