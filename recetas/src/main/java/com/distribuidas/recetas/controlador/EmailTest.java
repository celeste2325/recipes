package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.email.EmailClient;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emailtest")
@AllArgsConstructor
public class EmailTest {


    private final EmailClient emailClient;
    private final CredencialService credencialService;
    private final UsuarioService usuarioService;

    //TODO cambiar nombre
    @GetMapping("/forgot/{email}")
    public void forgotEmail(@PathVariable String email) {

        credencialService.forgotPassword(email);
    }


}
