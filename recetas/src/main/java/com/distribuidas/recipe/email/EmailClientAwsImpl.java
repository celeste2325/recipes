package com.distribuidas.recipe.email;

import com.distribuidas.recipe.email.template.TemplateManager;
import org.springframework.stereotype.Component;

@Component
public class EmailClientAwsImpl implements EmailClient {

    final private TemplateManager templateManager;

    final private AwsSesClient awsSesClient;

    public EmailClientAwsImpl(TemplateManager templateManager, AwsSesClient awsSesClient) {

        this.templateManager = templateManager;
        this.awsSesClient = awsSesClient;
    }

    @Override
    public void ForgotPassword(String code, String email) {
        var templateString = this.templateManager.GetForgotpasswordTemplate();

        templateString = templateString.replace("{URL}", "localhost:8080");
        templateString = templateString.replace("{code}", code);
        templateString = templateString.replace("{email}", email);

        var paramenters = new EmailParamenters();
        paramenters.setSubject("Recupero de contraseña");
        paramenters.setRecipientEmail(email);
        paramenters.setHtmlBody(templateString);

        awsSesClient.SendEmail(paramenters);

    }

    @Override
    public void ValidarAlumno(String code, String email) {
        var templateString = this.templateManager.GetValidarAlumnoTemplateString();
        templateString = templateString.replace("{URL}", "localhost:8080");
        templateString = templateString.replace("{code}", code);
        templateString = templateString.replace("{email}", email);

        var paramenters = new EmailParamenters();
        paramenters.setSubject("¡Bienvenido a AdiCook!");
        paramenters.setRecipientEmail(email);
        paramenters.setHtmlBody(templateString);

        awsSesClient.SendEmail(paramenters);
    }

    @Override
    public void NewRegister(String email) {
        var templateString = this.templateManager.GetNewRegisterTemplate();
        templateString = templateString.replace("{URL}", "localhost:8080");
        templateString = templateString.replace("{email}", email);

        var paramenters = new EmailParamenters();
        paramenters.setSubject("¡Bienvenido a AdiCook!");
        paramenters.setRecipientEmail(email);
        paramenters.setHtmlBody(templateString);

        awsSesClient.SendEmail(paramenters);
    }
}
