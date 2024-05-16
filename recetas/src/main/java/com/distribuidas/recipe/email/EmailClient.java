package com.distribuidas.recipe.email;

public interface EmailClient {

    void ForgotPassword(String code, String email);

    void NewRegister(String email);

    void validateStudent(String code, String email);

}
