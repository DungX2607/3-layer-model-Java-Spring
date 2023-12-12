package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountForm.AccountFilterForm;
import com.vti.testing.form.AccountForm.CreatingAccountForm;
import com.vti.testing.form.AccountForm.UpdateAccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAccountServices extends UserDetailsService {
    Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form);

    Account getAccountByUsername(String username);
    Account getAccountById(int id);

    void createAccount(CreatingAccountForm form);
    void updateAccount(UpdateAccountForm form);
    void deleteAccount(Integer id);


}
