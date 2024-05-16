package com.distribuidas.recipe.service.implementations;

import com.distribuidas.recipe.email.EmailClient;
import com.distribuidas.recipe.model.entities.Credential;
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

    CredentialRepository credentialRepository;

    UserRepository userRepository;
    EmailClient emailClient;

    @Autowired
    CredentialServiceImpl(CredentialRepository credentialRepository, UserRepository userRepository, EmailClient emailClient) {
        this.credentialRepository = credentialRepository;
        this.emailClient = emailClient;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Iterable<Credential> findAll() {
        return credentialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Credential> findById(int ID) {
        return credentialRepository.findById(ID);
    }

    public Optional<Credential> findByidUser(int userID) {
        return credentialRepository.findByidUser(userID);
    }

    @Transactional
    public void save(Credential credential) {
        credentialRepository.save(credential);
    }

    // este request es para enviar la solicitud de olvidar password
    @Transactional
    public void forgotPassword(String email) {
        var user = userRepository.findByMail(email).get();
        var verificationCode = GetVerificationCode();
        var credentials = user.getCredencialesByIdUsuario().stream().findFirst().get();
        credentials.setCodigoVerificacion(verificationCode);
        credentialRepository.save(credentials);
        emailClient.ForgotPassword(verificationCode, email);
    }

    public void isStudent(String email) {
        var user = userRepository.findByMail(email).get();
        var verificationCode = GetVerificationCode();
        var credentials = user.getCredencialesByIdUsuario().stream().findFirst().get();
        credentials.setCodigoVerificacion(verificationCode);
        credentialRepository.save(credentials);
        emailClient.validateStudent(verificationCode, email);
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
        var user = userRepository.findByMail(email).get();
        var credentials = user.getCredencialesByIdUsuario().stream().findFirst().get();

        if (credentials.getCodigoVerificacion().equals(code)) {
            credentials.setCodigoVerificacion(null);
            credentials.setContrasenia(password);
            credentialRepository.save(credentials);
        }


    }

    @Transactional
    public void deleteById(int ID) {
        credentialRepository.deleteById(ID);

    }

    @Transactional
    public void deleteByIdUser(int ID) {
        Optional<Credential> cred = findById(ID);
        credentialRepository.deleteById(cred.get().getId());

    }

    private String GetVerificationCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000; // Genera un número aleatorio de 100000 a 999999
        return String.valueOf(randomNumber);
    }
}
