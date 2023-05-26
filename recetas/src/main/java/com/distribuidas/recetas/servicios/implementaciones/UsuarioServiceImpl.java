package com.distribuidas.recetas.servicios.implementaciones;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.distribuidas.recetas.repositorios.UsuarioRepository;
import com.distribuidas.recetas.modelo.Usuario;

@Service
public class UsuarioServiceImpl {
	
	UsuarioRepository usuarioRepository;	
	
	@Autowired
	UsuarioServiceImpl(UsuarioRepository usuarioRepository){
		this.usuarioRepository=usuarioRepository;
	}

	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(int id) {
		return usuarioRepository.findById(id);
	}

	public Optional<Usuario> findByEmail(String nickname) {
		return usuarioRepository.findByNickname(nickname);
	}

	@Transactional
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Transactional
	public void deleteById(int id) {
		usuarioRepository.deleteById(id);
		
	}
	

}
