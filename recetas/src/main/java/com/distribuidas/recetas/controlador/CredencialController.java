package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credentials")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CredencialController {

    private CredencialService credencialService;

    @PostMapping("/verify-credentials")
    public void verifyCredentials(@RequestParam(name = "email") String email,@RequestParam(name = "code") String codigo ) {
       credencialService.verifyCredentials(email,codigo);
    }

    @PostMapping("/verify-pasword")
    public void verifynewPassword(@RequestParam(name = "email") String email,@RequestParam(name = "code") String codigo,@RequestParam(name = "password") String password ) {
        credencialService.verifyNewPassword(email,codigo,password);
    }


}
