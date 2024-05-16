package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.email.EmailClient;
import com.distribuidas.recipe.model.entities.Credencial;
import com.distribuidas.recipe.repository.CredentialRepository;
import com.distribuidas.recipe.repository.UserRepository;
import com.distribuidas.recipe.service.interfaces.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
public class CredentialServiceImpl implements CredentialService {

    CredentialRepository credencialRepository;

    UserRepository usuarioRepository;
    EmailClient emailClient;

    @Autowired
    CredentialServiceImpl(CredentialRepository credencialRepository, UserRepository usuarioRepository, EmailClient emailClient) {
        this.credencialRepository = credencialRepository;
        this.emailClient = emailClient;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Credencial> findAll() {
        return credencialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Credencial> findById(int id) {
        return credencialRepository.findById(id);
    }

    public Optional<Credencial> findByidUsuario(int idusuario) {
        return credencialRepository.findByidUsuario(idusuario);
    }

    @Transactional
    public void save(Credencial credencial) {
        credencialRepository.save(credencial);
    }

    // este request es para enviar la solicitud de olvidar password
    @Transactional
    public void forgotPassword(String email) {
        var usuario = usuarioRepository.findByMail(email).get();
        var codigoVer = GetCodigoVerificacion();
        var credentials = usuario.getCredencialesByIdUsuario().stream().findFirst().get();
        credentials.setCodigoVerificacion(codigoVer);
        credencialRepository.save(credentials);
        emailClient.ForgotPassword(codigoVer, email);
    }

    public void esAlumno(String email) {
        var usuario = usuarioRepository.findByMail(email).get();
        var codigoVer = GetCodigoVerificacion();
        var credentials = usuario.getCredencialesByIdUsuario().stream().findFirst().get();
        credentials.setCodigoVerificacion(codigoVer);
        credencialRepository.save(credentials);
        emailClient.ValidarAlumno(codigoVer, email);
    }

    /* este servicio es para cuando el usuario haga el request para verificar el email
    @Transactional
    public void verifyCredentials(String email, String code) {
        var usuario = usuarioRepository.findByMail(email).get();
        var credentials = usuario.getCredencialesByIdUsuario().stream().findFirst().get();

        if (!credentials.getCodigoVerificacion().equals(code) && usuario.getHabilitado().equals("NO")) {
            credentials.setCodigoVerificacion(null);
            credencialRepository.save(credentials);// no se si esta bien echo la cascada.
            usuario.setHabilitado("SI");
            usuarioRepository.save(usuario);
        }


    }*/

    // este servicio handlea el request cuando envia el link para forgot password
    // pone verifica que el codigo de verificacion para el forgot password sea el mismo enviado en el link
    // si lo es pone el codigo en vacio, y setea el nuevo password si no no hace nada, talvez tirar una exception ?
    @Transactional
    public void verifyNewPassword(String email, String code, String password) {
        var usuario = usuarioRepository.findByMail(email).get();
        var credentials = usuario.getCredencialesByIdUsuario().stream().findFirst().get();

        if (credentials.getCodigoVerificacion().equals(code)) {
            credentials.setCodigoVerificacion(null);
            credentials.setContrasenia(password);
            credencialRepository.save(credentials);
        }


    }

    @Transactional
    public void deleteById(int id) {
        credencialRepository.deleteById(id);

    }

    @Transactional
    public void deleteByidUsuario(int id) {
        Optional<Credencial> cred = findById(id);
        credencialRepository.deleteById(cred.get().getId());

    }

    private String GetCodigoVerificacion() {
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000; // Genera un número aleatorio de 100000 a 999999
        return String.valueOf(numero);
    }
}
