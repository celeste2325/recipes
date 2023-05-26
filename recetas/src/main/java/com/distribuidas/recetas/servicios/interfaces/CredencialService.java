package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.Credencial;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface CredencialService {
	
	public Iterable<Credencial> findAll();					
	
	public Optional<Credencial> findById(int id);
	
	public Optional<Credencial> findByidUsuario(int idusuario);

	public void save(Credencial credencial);			
	
	public void deleteById(int id);	
	
	public void deleteByidUsuario(int id);	

}
