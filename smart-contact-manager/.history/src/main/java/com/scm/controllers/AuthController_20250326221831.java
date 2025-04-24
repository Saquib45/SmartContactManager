package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.repositories.UserRepo;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;
    //verify Email
    @GetMapping("verify-email")
    public String verifyEmial(@RequestParam("token") String token){
        System.out.println("verigy Email");

        
        return null;
    }
}
