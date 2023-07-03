package com.distribuidas.recetas.email.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TemplateManager {

    private final String ForgotPasswordTemplateString;
    private final String GetNewRegisterTempalteString;
    private final String ValidarAlumnoTemplateString;

    @Autowired
    private ResourceLoader resourceLoader;

    public TemplateManager() throws IOException {
        ForgotPasswordTemplateString = FileToString(ResourceUtils.getFile("classpath:email/ForgotEmailTemplate_EN.html").toPath());
        GetNewRegisterTempalteString = FileToString(ResourceUtils.getFile("classpath:email/singUpTemplate_EN.html").toPath());
        ValidarAlumnoTemplateString = FileToString(ResourceUtils.getFile("classpath:email/ValidarAlumno.HTML").toPath());
    }

    private static String FileToString(Path path) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(path);

        // Leer el archivo línea por línea y concatenar las líneas en un StringBuilder
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            contenido.append(linea);
            contenido.append(System.lineSeparator());
        }

        // Cerrar el BufferedReader
        bufferedReader.close();

        // Convertir el StringBuilder en un String
        return contenido.toString();

    }

    public String GetForgotpasswordTemplate() {
        return ForgotPasswordTemplateString;


    }

    public String GetNewRegisterTemplate() {
        return GetNewRegisterTempalteString;


    }
    public String GetValidarAlumnoTemplateString() {
        return ValidarAlumnoTemplateString;

    }


}
