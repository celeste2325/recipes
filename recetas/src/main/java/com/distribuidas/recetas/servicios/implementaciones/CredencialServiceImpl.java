package com.distribuidas.recetas.servicios.implementaciones;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.distribuidas.recetas.modelo.Credencial;
import com.distribuidas.recetas.repositorios.CredencialRepository;


@Service
public class CredencialServiceImpl {
	
	CredencialRepository credencialRepository;
	
	@Autowired
	CredencialServiceImpl(CredencialRepository credencialRepository){
		this.credencialRepository=credencialRepository;
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

	@Transactional
	public void deleteById(int id) {
		credencialRepository.deleteById(id);
		
	}
	
	@Transactional
	public void deleteByidUsuario(int id) {
	Optional<Credencial> cred = findById(id);
	credencialRepository.deleteById(cred.get().getId());
	
}
}
