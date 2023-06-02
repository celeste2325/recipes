package com.distribuidas.recetas.email;

public interface EmailClient {

    public void ForgotPassword(String code, String email);
    public void NewRegister(String code, String email);

}
