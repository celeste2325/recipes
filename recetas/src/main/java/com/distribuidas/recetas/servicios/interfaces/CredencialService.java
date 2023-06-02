package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Credencial;

import java.util.Optional;

public interface CredencialService {

    public Iterable<Credencial> findAll();

    public Optional<Credencial> findById(int id);

    public Optional<Credencial> findByidUsuario(int idusuario);

    public void save(Credencial credencial);

    public void deleteById(int id);

    public void deleteByidUsuario(int id);

    void forgotPassword(String email);
    void verifyCredentials(String email, String code);
    void verifyNewPassword(String email, String code, String password);

}
