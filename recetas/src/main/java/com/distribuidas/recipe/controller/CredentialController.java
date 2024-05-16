package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.service.interfaces.CredentialService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CredentialController {

    private CredentialService credencialService;


    @PostMapping("/verify-pasword")
    public void verifynewPassword(@RequestParam(name = "email") String email, @RequestParam(name = "code") String codigo, @RequestParam(name = "password") String password) {
        credencialService.verifyNewPassword(email, codigo, password);
    }


}
