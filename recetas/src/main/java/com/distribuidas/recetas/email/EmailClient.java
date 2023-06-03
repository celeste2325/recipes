package com.distribuidas.recetas.email;

public interface EmailClient {

    void ForgotPassword(String code, String email);

    void NewRegister(String code, String email);

}
