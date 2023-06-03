package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.email.EmailClient;
import com.distribuidas.recetas.modelo.entities.Credencial;
import com.distribuidas.recetas.modelo.entities.Usuario;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/emailtest")
@AllArgsConstructor
public class EmailTest {



    private final  EmailClient emailClient;
    private final CredencialService credencialService;
    private final UsuarioService usuarioService;
    //TODO cambiar nombre
    @GetMapping("/forgot")
    public void forgotEmail(){
        credencialService.forgotPassword("juanvalero252@gmail.com");
    }
    //TODO borrar
    @GetMapping("/newreg")
    public void newreg(){
        emailClient.NewRegister("juanvalero252@gmail.com");
    }


}
