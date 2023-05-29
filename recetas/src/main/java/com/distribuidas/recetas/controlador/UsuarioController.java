package com.distribuidas.recetas.controlador;

import com.distribuidas.recetas.modelo.entities.Credencial;
import com.distribuidas.recetas.modelo.entities.Usuario;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/usuario")

@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;
    private CredencialService credencialService;

    @Autowired
    public UsuarioController(UsuarioService usuarioServ, CredencialService credencialServ) {
        this.usuarioService = usuarioServ;
        this.credencialService = credencialServ;

    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Crear un usuario
    @PostMapping("/alta")
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Crear credencial
    @PostMapping("/altacred")
    public ResponseEntity<?> createCred(@RequestBody Credencial credencial) {
        credencialService.save(credencial);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Login
    @GetMapping(path = "/login", params = {"alias", "contrasenia"})
    public ResponseEntity<?> login(@RequestParam String alias, @RequestParam String contrasenia) {
        Optional<Usuario> usuario = usuarioService.findByNickname(alias);
        System.out.println(usuario.get().getNombre());
        Optional<Credencial> credencial = credencialService.findByidUsuario(usuario.get().getIdUsuario());
        if (credencial.get().getContrasenia() != null && credencial.get().getContrasenia().equals(contrasenia)) {
            return ResponseEntity.ok(usuario.get().getNickname() + "/1");
        }
        return ResponseEntity.ok(" /0");
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Valida si existe el email
    @GetMapping(path = "/validarEmail", params = {"email"})
    public ResponseEntity<?> validarEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.findByMail(email);

        if (usuario.isPresent()) {
            System.out.println(usuario.get().getHabilitado());
            if (usuario.get().getHabilitado().equals("si"))
                return ResponseEntity.ok(usuario.get().getMail() + "El email ya esta en uso y habilitado");
            else
                return ResponseEntity.ok(usuario.get().getMail() + "El usuario no completó su registro");

        }
        return ResponseEntity.ok("El email no existe");
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Valida si existe el Alias
    @GetMapping(path = "/validarAlias", params = {"nickname"})
    public ResponseEntity<?> validarAlias(@RequestParam String nickname) {
        Optional<Usuario> usuario = usuarioService.findByNickname(nickname);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get().getNickname() + "El alias ya esta en uso.");
        }
        return ResponseEntity.ok("El alias esta libre.");
    }

    ////////////////////////////////////

    // METODO OK ------------ ESTE METODO SE RESUELVE DESDE EL FRONT

    ////////////////////////////////////

    /*
     * //Valida contraseña
     *
     * @GetMapping(path="/validarContrasenia",params= {"pwd1","pwd2"}) public
     * ResponseEntity<?> validarAlias(@RequestParam String pwd1, @RequestParam
     * String pwd2) {
     *
     * if (pwd1.equals(pwd2)) { return
     * ResponseEntity.ok("/1 /Contraseñas coinciden."); }else { return
     * ResponseEntity.ok("/0 /Contraseñas no coinciden."); } }
     */

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Cambio de contraseña
    @GetMapping(path = "/cambiarContrasenia", params = {"id", "pwd1", "pwd2"})
    public ResponseEntity<?> cambiarContrasenia(@RequestParam Integer id, @RequestParam String pwd1,
                                                @RequestParam String pwd2) {

        if (pwd1.equals(pwd2)) {
            Optional<Credencial> cred = credencialService.findByidUsuario(id);
            if (!cred.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                cred.get().setContrasenia(pwd1);
                credencialService.save(cred.get());
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } else {
            return ResponseEntity.ok("/0 /Contraseñas no coinciden.");
        }
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Pre-creación de usuario
    @PostMapping("/prealta")
    public ResponseEntity<?> prealta(@RequestBody Usuario usuario) {
        usuario.setHabilitado("no");
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Leer un usuario
    @GetMapping(path = "/buscar", params = {"id"})
    public ResponseEntity<?> leer(@RequestParam Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (!usuario.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario.get());
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // Actualizar un usuario
    @PutMapping("/modificar")
    public ResponseEntity<?> modificar(@RequestBody Usuario usuario) {
        Optional<Usuario> usr = usuarioService.findById(usuario.getIdUsuario());
        if (!usr.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            usr.get().setNombre(usuario.getNombre());
            usr.get().setMail(usuario.getMail());
            usr.get().setHabilitado(usuario.getHabilitado());
            usr.get().setNickname(usuario.getNickname());
            usr.get().setAvatar(usuario.getAvatar());
            usr.get().setTipoUsuario(usuario.getTipoUsuario());
            usuarioService.save(usr.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    /*
     * @PutMapping(path="/modificar",params={"nombre","apellido","documento","mail",
     * "pass"}) public ResponseEntity<?> updateParams(@RequestParam String
     * nombre, @RequestParam String apellido, @RequestParam String
     * documento, @RequestParam String mail,@RequestParam String pass ){
     * Optional<Usuario> u= usuarioService.findById(documento); if(u.isPresent()){
     * Usuario user=u.get(); user.setApellido(apellido); user.setNombre(nombre);
     * user.setEmail(mail); user.setPass(pass); usuarioService.save(user); return
     * ResponseEntity.ok().build(); } else{ return
     * ResponseEntity.notFound().build(); }
     *
     * }
     */

    // Borrar un usuario
    @DeleteMapping(path = "/eliminar", params = "id")
    public ResponseEntity<?> borrar(@RequestParam Integer id) {
        if (!usuarioService.findById(id).isPresent())
            return ResponseEntity.notFound().build();
        usuarioService.deleteById(id);
        credencialService.deleteByidUsuario(id);
        return ResponseEntity.ok().build();
    }

    ////////////////////////////////////

    // METODO OK

    ////////////////////////////////////

    // traer todas las personas
    @GetMapping("/buscarTodos")
    public List<Usuario> readAll() {
        List<Usuario> usuarios = StreamSupport.stream(usuarioService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return usuarios;
    }


    // REGISTRO SI ES ALUMNO

    @GetMapping(path = "/validarAlumno", params = {"email"})
    public ResponseEntity<?> validarAlumno(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.findByMail(email);

        if (usuario.isPresent() && usuario.get().getTipoUsuario().equals("Alumno")) {
            System.out.println(usuario.get().getTipoUsuario());
            return ResponseEntity.ok("Es Alumno");
        } else {
            return ResponseEntity.ok("No es Alumno o el Email no existe");

        }
    }

    // RECUPERAR CONTRASEÑA CON OTP
}
