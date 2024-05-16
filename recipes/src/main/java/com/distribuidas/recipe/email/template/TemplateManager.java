package com.distribuidas.recipe.email.template;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TemplateManager {

    private final String ForgotPasswordTemplateString;
    private final String GetNewRegisterTempalteString;
    private final String ValidarAlumnoTemplateString;


    public TemplateManager() throws IOException {
        ForgotPasswordTemplateString = FileToString("src/main/java/com/distribuidas/recetas/email/template/ForgotEmailTemplate_EN.html");
        GetNewRegisterTempalteString = FileToString("src/main/java/com/distribuidas/recetas/email/template/singUpTemplate_EN.html");
        ValidarAlumnoTemplateString = FileToString("src/main/java/com/distribuidas/recetas/email/template/ValidateStudent.HTML");

    }

    private static String FileToString(String templateDir) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Path.of(templateDir));

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
