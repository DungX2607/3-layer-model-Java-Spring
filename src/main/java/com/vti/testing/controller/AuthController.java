package com.vti.testing.controller;

import com.vti.testing.dto.LoginInforDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.service.IAccountServices;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@ResponseBody
@CrossOrigin(origins = {"http://localhost:5500","http://127.0.0.1:5500"})
public class AuthController {
    @Autowired
    private IAccountServices accountService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/login")
    public LoginInforDTO login(Principal principal) {
        String username = principal.getName();
        Account account = accountService.getAccountByUsername(username);
        return modelMapper.map(account, LoginInforDTO.class);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout success!!!";
    }
}
