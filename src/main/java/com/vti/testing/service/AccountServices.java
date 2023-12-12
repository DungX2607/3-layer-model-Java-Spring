package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.entity.Group;
import com.vti.testing.form.AccountForm.AccountFilterForm;
import com.vti.testing.form.AccountForm.CreatingAccountForm;
import com.vti.testing.form.AccountForm.UpdateAccountForm;
import com.vti.testing.respository.IAccountRespository;
import com.vti.testing.respository.IGrouptRespository;
import com.vti.testing.specification.AccountSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServices implements IAccountServices {

    @Autowired
    private IAccountRespository accountRespository;
    @Autowired
    private IGrouptRespository grouptRespository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form) {
        Specification<Account> where = AccountSpecification.biuldWhere(form);
        return accountRespository.findAll(where, pageable);
    }

    @Override
    public Account getAccountById(int id) {
        return accountRespository.findById(id).get();
    }


    @Override
    public Account getAccountByUsername(String username) {
        return accountRespository.findByUserName(username);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void createAccount(CreatingAccountForm form) {
        Account account = modelMapper.map(form, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRespository.save(account);
        List<Group> groups = account.getGroups();
        groups.forEach(group -> group.setCreator(account));
        grouptRespository.saveAll(groups);

    }

    @Override
    public void updateAccount(UpdateAccountForm form) {
        Account oldAccount = accountRespository.findById(form.getId()).get();
        if(form.getCreateDate()==null){
            form.setCreateDate(oldAccount.getCreateDate());
        }
        Account account = modelMapper.map(form, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRespository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username, account.getPassword(), Collections.emptyList());
    }


    @Override
    public void deleteAccount(Integer id) {
        Account accountsToDelete= accountRespository.findById(id).get();
        accountsToDelete.getGroups().removeAll(accountsToDelete.getGroups());
        accountRespository.delete(accountsToDelete);


    }
}
