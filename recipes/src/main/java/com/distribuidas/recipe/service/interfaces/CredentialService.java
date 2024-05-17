package com.distribuidas.recipe.service.interfaces;

import com.distribuidas.recipe.model.entities.Credential;

import java.util.Optional;

public interface CredentialService {

    Iterable<Credential> findAll();

    Optional<Credential> findById(int ID);

    Optional<Credential> findByUserID(int userID);

    void save(Credential credential);

    void deleteById(int ID);

    void deleteByUserID(int ID);

    void forgotPassword(String email);

    void isStudent(String email);

    void verifyNewPassword(String email, String code, String password);

}
