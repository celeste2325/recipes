package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.email.EmailClient;
import com.distribuidas.recipe.service.interfaces.CredentialService;
import com.distribuidas.recipe.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailtest")
@AllArgsConstructor
public class EmailController {


    private final EmailClient emailClient;
    private final CredentialService credencialService;
    private final UserService userService;

    //TODO cambiar nombre
    @GetMapping("/forgot/{email}")
    public void forgotEmail(@PathVariable String email) {

        credencialService.forgotPassword(email);
    }


}
