package com.distribuidas.recetas.servicios.interfaces;

import com.distribuidas.recetas.modelo.entities.Credencial;

import java.util.Optional;

public interface CredencialService {

    Iterable<Credencial> findAll();

    Optional<Credencial> findById(int id);

    Optional<Credencial> findByidUsuario(int idusuario);

    void save(Credencial credencial);

    void deleteById(int id);

    void deleteByidUsuario(int id);

    void forgotPassword(String email);
    void verifyCredentials(String email, String code);
    void verifyNewPassword(String email, String code, String password);

}
