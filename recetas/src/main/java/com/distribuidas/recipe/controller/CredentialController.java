package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.service.interfaces.CredentialService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CredentialController {

    private CredentialService credentialService;


    @PostMapping("/verify-pasword")
    public void verifyNewPassword(@RequestParam(name = "email") String email, @RequestParam(name = "code") String code, @RequestParam(name = "password") String password) {
        credentialService.verifyNewPassword(email, code, password);
    }


}
