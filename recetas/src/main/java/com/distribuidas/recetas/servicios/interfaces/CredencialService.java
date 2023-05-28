package com.distribuidas.recetas.servicios.interfaces;

import java.util.Optional;

import com.distribuidas.recetas.modelo.Credencial;

public interface CredencialService {

	public Iterable<Credencial> findAll();

	public Optional<Credencial> findById(int id);

	public Optional<Credencial> findByidUsuario(int idusuario);

	public void save(Credencial credencial);

	public void deleteById(int id);

	public void deleteByidUsuario(int id);

}
