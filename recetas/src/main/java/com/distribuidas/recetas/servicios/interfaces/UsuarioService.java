package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {
	
	public Iterable<Usuario> findAll();					
	
	public Optional<Usuario> findById(int id);

	public Optional<Usuario> findByNickname(String nickname);
	
	public Optional<Usuario> findByMail(String email);

	public void save(Usuario usuario);			
	
	public void deleteById(int id);	
	
	

}
