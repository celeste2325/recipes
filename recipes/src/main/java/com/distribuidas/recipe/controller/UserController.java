package com.distribuidas.recipe.controller;

import com.distribuidas.recipe.email.EmailClient;
import com.distribuidas.recipe.model.entities.Credential;
import com.distribuidas.recipe.model.entities.User;
import com.distribuidas.recipe.service.interfaces.CredentialService;
import com.distribuidas.recipe.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/usuario")

@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final CredentialService credentialService;
    private final EmailClient emailClient;

    @Autowired
    public UserController(UserService userService, CredentialService credentialService, EmailClient emailClient) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.emailClient = emailClient;

    }


    // Valida si existe el email
    @GetMapping(path = "/validarEmail", params = {"email"})
    public ResponseEntity<?> validateEmail(@RequestParam String email) {
        Optional<User> user = userService.findByMail(email);

        if (user.isPresent()) {
            System.out.println(user.get().getEnabled());
            if (user.get().getEnabled().equals("si"))
                return ResponseEntity.ok("1");
            else
                return ResponseEntity.ok("2");
        }
        return ResponseEntity.ok("0");
    }


    // Valida si existe el Alias
    @GetMapping(path = "/validateNickname", params = {"nickname"})
    public ResponseEntity<?> validateNickname(@RequestParam String nickname) {
        Optional<User> user = userService.findByNickname(nickname);
        if (nickname.equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "empty nickname");
        }
        if (user.isPresent()) {
            List<String> aliasOption = userService.getAliasOption(user.get().getNickname());
            return ResponseEntity.ok(aliasOption);
        }
        return ResponseEntity.ok("0");
    }


    // Pre-creación de usuario
    @PostMapping("/pre_registration")
    public ResponseEntity<?> pre_registration(@RequestBody User user) {
        Optional<User> usr = userService.findByNickname(user.getNickname());
        user.setEnabled("no");
        userService.save(user);
        emailClient.NewRegister(user.getMail());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    // Crear un usuario
    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Optional<User> usr = userService.findByMail(user.getMail());
        usr.get().setName(user.getName());
        usr.get().setMail(user.getMail());
        usr.get().setEnabled(user.getEnabled());
        usr.get().setNickname(user.getNickname());
        usr.get().setAvatar(user.getAvatar());
        usr.get().setType_user(user.getType_user());
        userService.save(usr.get());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // Crear credencial
    @PostMapping()
    public ResponseEntity<?> createCredential(@RequestBody Credential credential) {
        Optional<Credential> cred = credentialService.findByidUser(credential.getIdUsuario());
        if (cred.isPresent()) {
            cred.get().setIdUsuario(credential.getIdUsuario());
            cred.get().setContrasenia(credential.getContrasenia());
            cred.get().setCodigoVerificacion(credential.getCodigoVerificacion());
            credentialService.save(cred.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            credentialService.save(credential);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }


    // Login
    @GetMapping(path = "/login", params = {"nickname", "password"})
    public ResponseEntity<?> login(@RequestParam String nickname, @RequestParam String password) {
        Optional<User> user = userService.findByNickname(nickname);
        System.out.println(user.get().getName());
        Optional<Credential> credential = credentialService.findByidUser(user.get().getUserID());
        if (credential.get().getContrasenia() != null && credential.get().getContrasenia().equals(password)) {
            return new ResponseEntity<>(credential.get(), HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
    }


    // Cambio de contraseña
    @GetMapping(path = "/changePassword", params = {"id", "pwd1", "pwd2"})
    public ResponseEntity<?> changePassword(@RequestParam Integer ID, @RequestParam String pwd1,
											@RequestParam String pwd2) {

        if (pwd1.equals(pwd2)) {
            Optional<Credential> cred = credentialService.findByidUser(ID);
            if (!cred.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                cred.get().setContrasenia(pwd1);
                credentialService.save(cred.get());
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } else {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");

        }
    }


    // REGISTRO SI ES ALUMNO

    @GetMapping(path = "/validarAlumno", params = {"email"})
    public ResponseEntity<?> validateStudent(@RequestParam String email) {
        Optional<User> user = userService.findByMail(email);

        if (user.isPresent() && user.get().getType_user().equals("Student")) {
            System.out.println(user.get().getType_user());
            //credencialService.esAlumno(email);
            return ResponseEntity.ok("1");
        } else {
            return ResponseEntity.ok("0");
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Student or Email does not exist");
        }
    }


    @GetMapping(path = "/forgot", params = {"email"})
    public ResponseEntity<?> recuperoContraseniaOTP(@RequestParam String email) {
        credentialService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping(path = "/forgotAlumno", params = {"email"})
    public ResponseEntity<?> recuperoContraseniaOTPAlumno(@RequestParam String email) {
        credentialService.isStudent(email);
        return ResponseEntity.status(HttpStatus.OK).build();

    }


    @GetMapping(path = "/verificarOTP", params = {"email", "code"})
    public ResponseEntity<?> verificarOTP(@RequestParam String email, @RequestParam String code) {

        Optional<User> user = userService.findByMail(email);
        Optional<Credential> credential = credentialService.findByidUser(user.get().getUserID());
        if (credential.get().getCodigoVerificacion().equals(code)) {
            credential.get().setCodigoVerificacion(null);
            credentialService.save(credential.get());
            return ResponseEntity.ok("1");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Codes do not match");

    }


    /*
     * Valida primer login
     *
     * @GetMapping(path = "/validarPrimerLogin", params = {"email"}) public
     * ResponseEntity<?> validarPrimerLogin(@RequestParam String email) {
     * Optional<Usuario> usuario = usuarioService.findByMail(email);
     *
     * if (usuario.isPresent() && usuario.get().getNombre().equals("99999")) {
     * return ResponseEntity.ok("1");} else { return ResponseEntity.ok("2");
     *
     * }
     *
     * }
     */


    // Leer un usuario
    @GetMapping(path = "/getByEmail", params = {"email"})
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByMail(email);
        System.out.println(user.get().getUserID());
        if (!user.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get());
    }

    // Leer un usuario x alias
    @GetMapping(path = "/getByNickname", params = {"nickname"})
    public ResponseEntity<?> getByNickname(@RequestParam String nickname) {
        Optional<User> user = userService.findByNickname(nickname);
        System.out.println(user.get().getUserID());
        if (!user.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get());
    }


    // Leer un usuario
    @GetMapping(path = "/getNicknameByEmail", params = {"email"})
    public ResponseEntity<?> getNicknameByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByMail(email);
        System.out.println(user.get().getNickname());
        if (!user.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get().getNickname());
    }


    // Leer un usuario
    @GetMapping(path = "/getUserByID", params = {"id"})
    public ResponseEntity<?> getUserByID(@RequestParam Integer id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get());
    }


    // Actualizar un usuario
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody User user) {
        Optional<User> usr = userService.findById(user.getUserID());
        if (!usr.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            usr.get().setName(user.getName());
            usr.get().setMail(user.getMail());
            usr.get().setEnabled(user.getEnabled());
            usr.get().setNickname(user.getNickname());
            usr.get().setAvatar(user.getAvatar());
            usr.get().setType_user(user.getType_user());
            userService.save(usr.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }


    // Actualizar un ApeNom
    @PutMapping(path = "/editFullName", params = {"nickname", "fullName"})
    public ResponseEntity<?> editFullName(@RequestParam String nickname, String fullName) {
        Optional<User> user = userService.findByNickname(nickname);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            user.get().setName(fullName);
            userService.save(user.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }


    // Borrar un usuario
    @DeleteMapping(path = "/delete", params = "id")
    public ResponseEntity<?> delete(@RequestParam Integer ID) {
        if (!userService.findById(ID).isPresent())
            return ResponseEntity.notFound().build();
        userService.deleteById(ID);
        credentialService.deleteByIdUser(ID);
        // return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // traer todas las personas
    @GetMapping("/getAll")
    public List<User> getAll() {
        List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return users;
    }

    @GetMapping("partialSearch/{partialNameUser}")
    public List<User> getUsersByPartialName(@PathVariable String partialNameUser) {
        if (partialNameUser.length() >= 2) {
            return this.userService.getUsersByPartialName(partialNameUser);
        }
        return null;// agregar excepcion
    }


    // Actualizar Avatar
    @PutMapping(path = "/editAvatar", params = {"nickname", "avatar"})
    public ResponseEntity<?> editAvatar(@RequestParam String nickname, String avatar) {
        Optional<User> usr = userService.findByNickname(nickname);
        if (!usr.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            System.out.println(usr.get().getAvatar());
            usr.get().setAvatar(avatar);
            userService.save(usr.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}
