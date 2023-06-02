package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.email.EmailClient;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailtest")
@AllArgsConstructor
public class EmailTest {



    private final  EmailClient emailClient;
    private final CredencialService credencialService;

    @GetMapping("/forgot")
    public void forgotEmail(){
        credencialService.forgotPassword("juanvalero252@gmail.com");
    }

    @GetMapping("/newreg")
    public void newreg(){
        emailClient.NewRegister("1234","juanvalero252@gmail.com");
    }


}
