package com.distribuidas.recetas.controlador;

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

import com.distribuidas.recetas.email.EmailClient;
import com.distribuidas.recetas.modelo.entities.Credencial;
import com.distribuidas.recetas.modelo.entities.Usuario;
import com.distribuidas.recetas.servicios.interfaces.CredencialService;
import com.distribuidas.recetas.servicios.interfaces.UsuarioService;

@RestController
@RequestMapping("/usuario")

@CrossOrigin(origins = "*")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final CredencialService credencialService;
	private final EmailClient emailClient;

	@Autowired
	public UsuarioController(UsuarioService usuarioServ, CredencialService credencialServ, EmailClient emailClient) {
		this.usuarioService = usuarioServ;
		this.credencialService = credencialServ;
		this.emailClient = emailClient;

	}



	// Valida si existe el email
	@GetMapping(path = "/validarEmail", params = { "email" })
	public ResponseEntity<?> validarEmail(@RequestParam String email) {
		Optional<Usuario> usuario = usuarioService.findByMail(email);

		if (usuario.isPresent()) {
			System.out.println(usuario.get().getHabilitado());
			if (usuario.get().getHabilitado().equals("si"))
				return ResponseEntity.ok("1");
			else
				return ResponseEntity.ok("2");
		}
		return ResponseEntity.ok("0");
	}



	// Valida si existe el Alias
	@GetMapping(path = "/validarAlias", params = { "nickname" })
	public ResponseEntity<?> validarAlias(@RequestParam String nickname) {
		Optional<Usuario> usuario = usuarioService.findByNickname(nickname);
		if (nickname.equals("")) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Alias está vacío.");
		}
		if (usuario.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El alias existe.");
		}
		return ResponseEntity.ok("0");
	}



	// Pre-creación de usuario
	@PostMapping("/prealta")
	public ResponseEntity<?> prealta(@RequestBody Usuario usuario) {
		Optional<Usuario> usr = usuarioService.findByNickname(usuario.getNickname());
		if (usr.isPresent()) {
			List <String> alternativasAlias = usuarioService.opcionesAlias(usuario.getNickname());
			return ResponseEntity.ok(alternativasAlias);
		} else {
		usuario.setHabilitado("no");
		usuarioService.save(usuario);
		emailClient.NewRegister(usuario.getMail());
		return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}


	// Crear un usuario
	@PostMapping("/alta")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		Optional<Usuario> usr = usuarioService.findByMail(usuario.getMail());
		usr.get().setNombre(usuario.getNombre());
		usr.get().setMail(usuario.getMail());
		usr.get().setHabilitado(usuario.getHabilitado());
		usr.get().setNickname(usuario.getNickname());
		usr.get().setAvatar(usuario.getAvatar());
		usr.get().setTipoUsuario(usuario.getTipoUsuario());
		usuarioService.save(usr.get());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	// Crear credencial
	@PostMapping("/altacred")
	public ResponseEntity<?> createCred(@RequestBody Credencial credencial) {
		Optional <Credencial> cred = credencialService.findByidUsuario(credencial.getIdUsuario());
		if (cred.isPresent()) {
		cred.get().setIdUsuario(credencial.getIdUsuario());
		cred.get().setContrasenia(credencial.getContrasenia());
		cred.get().setCodigoVerificacion(credencial.getCodigoVerificacion());
		credencialService.save(cred.get());
		return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			credencialService.save(credencial);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}



	// Login
	@GetMapping(path = "/login", params = { "alias", "contrasenia" })
	public ResponseEntity<?> login(@RequestParam String alias, @RequestParam String contrasenia) {
		Optional<Usuario> usuario = usuarioService.findByNickname(alias);
		System.out.println(usuario.get().getNombre());
		Optional<Credencial> credencial = credencialService.findByidUsuario(usuario.get().getIdUsuario());
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
			Optional<Credencial> cred = credencialService.findByidUsuario(id);
			if (!cred.isPresent()) {
				return ResponseEntity.notFound().build();
			} else {
				cred.get().setContrasenia(pwd1);
				credencialService.save(cred.get());
				return ResponseEntity.status(HttpStatus.CREATED).build();
			}
		} else {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contraseñas no coinciden");

		}
	}



	// REGISTRO SI ES ALUMNO

	@GetMapping(path = "/validarAlumno", params = { "email" })
	public ResponseEntity<?> validarAlumno(@RequestParam String email) {
		Optional<Usuario> usuario = usuarioService.findByMail(email);

		if (usuario.isPresent() && usuario.get().getTipoUsuario().equals("Alumno")) {
			System.out.println(usuario.get().getTipoUsuario());
			credencialService.esAlumno(email);
			return ResponseEntity.ok("Es Alumno");
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es Alumno o el Email no existe");
		}
	}



	@GetMapping(path = "/forgot", params = { "email" })
	public ResponseEntity<?> recuperoContraseniaOTP(@RequestParam String email) {
		credencialService.forgotPassword(email);
		return ResponseEntity.status(HttpStatus.OK).build();

	}



	@GetMapping(path = "/verificarOTP", params = { "email", "code" })
	public ResponseEntity<?> verificarOTP(@RequestParam String email, @RequestParam String code) {

		Optional<Usuario> usuario = usuarioService.findByMail(email);
		Optional<Credencial> credencial = credencialService.findByidUsuario(usuario.get().getIdUsuario());
		if (credencial.get().getCodigoVerificacion().equals(code)) {
			credencial.get().setCodigoVerificacion(null);
			credencialService.save(credencial.get());
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
		Optional<Usuario> usuario = usuarioService.findByMail(email);
		System.out.println(usuario.get().getIdUsuario());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}

	// Leer un usuario x alias
	@GetMapping(path = "/buscarxalias", params = { "alias" })
	public ResponseEntity<?> buscarxalias(@RequestParam String alias) {
		Optional<Usuario> usuario = usuarioService.findByNickname(alias);
		System.out.println(usuario.get().getIdUsuario());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}



	// Leer un usuario
	@GetMapping(path = "/buscarxemailalias", params = { "email" })
	public ResponseEntity<?> buscarxemailalias(@RequestParam String email) {
		Optional<Usuario> usuario = usuarioService.findByMail(email);
		System.out.println(usuario.get().getNickname());
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get().getNickname());
	}


	// Leer un usuario
	@GetMapping(path = "/buscar", params = { "id" })
	public ResponseEntity<?> leer(@RequestParam Integer id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario.get());
	}


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


	// Actualizar un ApeNom
	@PutMapping(path = "/modificarApeNom", params = {"alias", "apenom"})
	public ResponseEntity<?> modificarapenom(@RequestParam String alias, String apenom) {
		Optional<Usuario> usr = usuarioService.findByNickname(alias);
		if (!usr.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			usr.get().setNombre(apenom);
			usuarioService.save(usr.get());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}


	// Borrar un usuario
	@DeleteMapping(path = "/eliminar", params = "id")
	public ResponseEntity<?> borrar(@RequestParam Integer id) {
		if (!usuarioService.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		usuarioService.deleteById(id);
		credencialService.deleteByidUsuario(id);
		// return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.OK).build();
	}


	// traer todas las personas
	@GetMapping("/buscarTodos")
	public List<Usuario> readAll() {
		List<Usuario> usuarios = StreamSupport.stream(usuarioService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return usuarios;
	}

	@GetMapping("busquedaParcial/{nombreParcialUsuario}")
	public List<Usuario> devuelveTiposPorBusquedaParcial(@PathVariable String nombreParcialUsuario) {
		if (nombreParcialUsuario.length() >= 2) {
			return this.usuarioService.devolverUsuariosPorBusquedaParcialNombre(nombreParcialUsuario);
		}
		return null;// agregar excepcion
	}


	// Actualizar Avatar
	@PutMapping(path = "/modificarAvatar", params = {"alias", "avatar"})
	public ResponseEntity<?> modificarAvatar(@RequestParam String alias, String avatar) {
		Optional<Usuario> usr = usuarioService.findByNickname(alias);
		if (!usr.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			System.out.println(usr.get().getAvatar());
			usr.get().setAvatar(avatar);
			usuarioService.save(usr.get());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	}
}
