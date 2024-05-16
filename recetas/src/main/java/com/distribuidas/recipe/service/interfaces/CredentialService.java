package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Credencial;

import java.util.Optional;

public interface CredentialService {

    Iterable<Credencial> findAll();

    Optional<Credencial> findById(int id);

    Optional<Credencial> findByidUsuario(int idusuario);

    void save(Credencial credencial);

    void deleteById(int id);

    void deleteByidUsuario(int id);

    void forgotPassword(String email);

    void esAlumno(String email);

    void verifyNewPassword(String email, String code, String password);

}
