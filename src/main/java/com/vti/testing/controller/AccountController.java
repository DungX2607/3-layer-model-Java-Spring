package com.vti.testing.controller;


import com.vti.testing.dto.AccountDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.form.AccountForm.AccountFilterForm;
import com.vti.testing.form.AccountForm.CreatingAccountForm;
import com.vti.testing.form.AccountForm.UpdateAccountForm;
import com.vti.testing.service.IAccountServices;
import com.vti.testing.validate.AccountIdNotExists;
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
@RequestMapping(value = "/api/v1/accounts", produces = "application/json")
@ResponseBody
@CrossOrigin(origins = {"http://localhost:5500","http://127.0.0.1:5500"})
@Validated
public class AccountController {

    @Autowired
    private IAccountServices accountServices;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<AccountDTO> getAllAccount(Pageable pageable, AccountFilterForm form) {
        Page<Account> accountPage = accountServices.getAllAccounts(pageable, form);
        List<Account> accounts = accountPage.getContent();
        List<AccountDTO> accountDTOS =
                accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(accountDTOS, pageable, accountPage.getTotalElements());
    }
    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable @AccountIdNotExists int id){
        Account account = accountServices.getAccountById(id);
//        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getPassword() , ....)
        return modelMapper.map(account, AccountDTO.class);
    }


    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody @Valid CreatingAccountForm form) {
        accountServices.createAccount(form);
        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable  int id, @RequestBody @Valid UpdateAccountForm form) {
        form.setId(id);
        accountServices.updateAccount(form);
        return  new ResponseEntity<>("Update successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable @AccountIdNotExists int id){
        accountServices.deleteAccount(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }


}
