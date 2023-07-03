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

    public TemplateManager() throws IOException {
        ForgotPasswordTemplateString = FileToString(getClass().getResourceAsStream("/email/ForgotEmailTemplate_EN.html"));
        GetNewRegisterTempalteString = FileToString(getClass().getResourceAsStream("/email/singUpTemplate_EN.html"));
        ValidarAlumnoTemplateString = FileToString(getClass().getResourceAsStream("/email/ValidarAlumno.HTML"));
    }

    private static String FileToString(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

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
