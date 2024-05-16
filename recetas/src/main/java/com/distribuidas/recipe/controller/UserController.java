package com.distribuidas.recipe.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.distribuidas.recipe.email.EmailClient;
import com.distribuidas.recipe.model.entities.Credential;
import com.distribuidas.recipe.model.entities.User;
import com.distribuidas.recipe.service.interfaces.CredentialService;
import com.distribuidas.recipe.service.interfaces.UserService;

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
	@GetMapping(path = "/validarEmail", params = { "email" })
	public ResponseEntity<?> validateEmail(@RequestParam String email) {
		Optional<User> user = userService.findByMail(email);

		if (user.isPresent()) {
			System.out.println(user.get().getHabilitado());
			if (user.get().getHabilitado().equals("si"))
				return ResponseEntity.ok("1");
			else
				return ResponseEntity.ok("2");
		}
		return ResponseEntity.ok("0");
	}



	// Valida si existe el Alias
	@GetMapping(path = "/validarAlias", params = { "nickname" })
	public ResponseEntity<?> validateNickname(@RequestParam String nickname) {
		Optional<User> user = userService.findByNickname(nickname);
		if (nickname.equals("")) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "empty nickname");
		}
		if (user.isPresent()) {
			List <String> alternativasAlias = userService.opcionesAlias(user.get().getNickname());
			return ResponseEntity.ok(alternativasAlias);
		}
		return ResponseEntity.ok("0");
	}



	// Pre-creación de usuario
	@PostMapping("/prealta")
	public ResponseEntity<?> prealta(@RequestBody User usuario) {
		Optional<User> usr = userService.findByNickname(usuario.getNickname());
		usuario.setHabilitado("no");
		userService.save(usuario);
		emailClient.NewRegister(usuario.getMail());
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}


	// Crear un usuario
	@PostMapping("/alta")
	public ResponseEntity<?> create(@RequestBody User usuario) {
		Optional<User> usr = userService.findByMail(usuario.getMail());
		usr.get().setNombre(usuario.getNombre());
		usr.get().setMail(usuario.getMail());
		usr.get().setHabilitado(usuario.getHabilitado());
		usr.get().setNickname(usuario.getNickname());
		usr.get().setAvatar(usuario.getAvatar());
		usr.get().setTipoUsuario(usuario.getTipoUsuario());
		userService.save(usr.get());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	// Crear credencial
	@PostMapping("/altacred")
	public ResponseEntity<?> createCred(@RequestBody Credential credencial) {
		Optional <Credential> cred = credentialService.findByidUser(credencial.getIdUsuario());
		if (cred.isPresent()) {
		cred.get().setIdUsuario(credencial.getIdUsuario());
		cred.get().setContrasenia(credencial.getContrasenia());
		cred.get().setCodigoVerificacion(credencial.getCodigoVerificacion());
		credentialService.save(cred.get());
		return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			credentialService.save(credencial);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}




	// Login
	@GetMapping(path = "/login", params = { "alias", "contrasenia" })
	public ResponseEntity<?> login(@RequestParam String alias, @RequestParam String contrasenia) {
		Optional<User> usuario = userService.findByNickname(alias);
		System.out.println(usuario.get().getNombre());
		Optional<Credential> credencial = credentialService.findByidUser(usuario.get().getIdUsuario());
		if (credencial.get().getContrasenia() != null && credencial.get().getContrasenia().equals(contrasenia)) {
			return new ResponseEntity<>(credencial.get(), HttpStatus.OK);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario no encontrado");
	}



	// Cambio de contraseña
	@GetMapping(path = "/cambiarContrasenia", params = { "id", "pwd1", "pwd2" })
	public ResponseEntity<?> cambiarContrasenia(@RequestParam Integer id, @RequestParam String pwd1,
			@RequestParam String pwd2) {

		if (pwd1.equals(pwd2)) {
			Optional<Credential> cred = credentialService.findByidUser(id);
			if (!cred.isPresent()) {
				return ResponseEntity.notFound().build();
			} else {
				cred.get().setContrasenia(pwd1);
				credentialService.save(cred.get());
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contraseñas no coinciden");

		}
	}



	// REGISTRO SI ES ALUMNO

	@GetMapping(path = "/validarAlumno", params = { "email" })
	public ResponseEntity<?> validarAlumno(@RequestParam String email) {
		Optional<User> usuario = userService.findByMail(email);

		if (usuario.isPresent() && usuario.get().getTipoUsuario().equals("Alumno")) {
			System.out.println(usuario.get().getTipoUsuario());
			//credencialService.esAlumno(email);
			return ResponseEntity.ok("1");
		} else {
			return ResponseEntity.ok("0");
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es Alumno o el Email no existe");
		}
	}



	@GetMapping(path = "/forgot", params = { "email" })
	public ResponseEntity<?> recuperoContraseniaOTP(@RequestParam String email) {
		credentialService.forgotPassword(email);
		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@GetMapping(path = "/forgotAlumno", params = { "email" })
	public ResponseEntity<?> recuperoContraseniaOTPAlumno(@RequestParam String email) {
		credentialService.isStudent(email);
		return ResponseEntity.status(HttpStatus.OK).build();

	}


	@GetMapping(path = "/verificarOTP", params = { "email", "code" })
	public ResponseEntity<?> verificarOTP(@RequestParam String email, @RequestParam String code) {

		Optional<User> usuario = userService.findByMail(email);
		Optional<Credential> credencial = credentialService.findByidUser(usuario.get().getIdUsuario());
		if (credencial.get().getCodigoVerificacion().equals(code)) {
			credencial.get().setCodigoVerificacion(null);
			credentialService.save(credencial.get());
			return ResponseEntity.ok("1");
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Códigos no coinciden");

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
	@GetMapping(path = "/buscarxemail", params = { "email" })
	public ResponseEntity<?> buscarxemail(@RequestParam String email) {
		Optional<User> usuario = userService.findByMail(email);
		System.out.println(usuario.get().getIdUsuario());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}

	// Leer un usuario x alias
	@GetMapping(path = "/buscarxalias", params = { "alias" })
	public ResponseEntity<?> buscarxalias(@RequestParam String alias) {
		Optional<User> usuario = userService.findByNickname(alias);
		System.out.println(usuario.get().getIdUsuario());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}



	// Leer un usuario
	@GetMapping(path = "/buscarxemailalias", params = { "email" })
	public ResponseEntity<?> buscarxemailalias(@RequestParam String email) {
		Optional<User> usuario = userService.findByMail(email);
		System.out.println(usuario.get().getNickname());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get().getNickname());
	}


	// Leer un usuario
	@GetMapping(path = "/buscar", params = { "id" })
	public ResponseEntity<?> leer(@RequestParam Integer id) {
		Optional<User> usuario = userService.findById(id);
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}


	// Actualizar un usuario
	@PutMapping("/modificar")
	public ResponseEntity<?> modificar(@RequestBody User usuario) {
		Optional<User> usr = userService.findById(usuario.getIdUsuario());
		if (!usr.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			usr.get().setNombre(usuario.getNombre());
			usr.get().setMail(usuario.getMail());
			usr.get().setHabilitado(usuario.getHabilitado());
			usr.get().setNickname(usuario.getNickname());
			usr.get().setAvatar(usuario.getAvatar());
			usr.get().setTipoUsuario(usuario.getTipoUsuario());
			userService.save(usr.get());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}


	// Actualizar un ApeNom
	@PutMapping(path = "/modificarApeNom", params = {"alias", "apenom"})
	public ResponseEntity<?> modificarapenom(@RequestParam String alias, String apenom) {
		Optional<User> usr = userService.findByNickname(alias);
		if (!usr.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			usr.get().setNombre(apenom);
			userService.save(usr.get());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}


	// Borrar un usuario
	@DeleteMapping(path = "/eliminar", params = "id")
	public ResponseEntity<?> borrar(@RequestParam Integer id) {
		if (!userService.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		userService.deleteById(id);
		credentialService.deleteByIdUser(id);
		// return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.OK).build();
	}


	// traer todas las personas
	@GetMapping("/buscarTodos")
	public List<User> readAll() {
		List<User> usuarios = StreamSupport.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return usuarios;
	}

	@GetMapping("busquedaParcial/{nombreParcialUsuario}")
	public List<User> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialUsuario) {
		if (nombreParcialUsuario.length() >= 2) {
			return this.userService.getUsersByPartialName(nombreParcialUsuario);
		}
		return null;// agregar excepcion
	}


	// Actualizar Avatar
	@PutMapping(path = "/modificarAvatar", params = {"alias", "avatar"})
	public ResponseEntity<?> modificarAvatar(@RequestParam String alias, String avatar) {
		Optional<User> usr = userService.findByNickname(alias);
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
