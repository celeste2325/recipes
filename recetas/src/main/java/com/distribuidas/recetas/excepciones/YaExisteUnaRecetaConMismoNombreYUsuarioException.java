package com.distribuidas.recetas.excepciones;

public class YaExisteUnaRecetaConMismoNombreYUsuarioException extends Exception {
    public YaExisteUnaRecetaConMismoNombreYUsuarioException(String message) {
        super(message);
    }

}
