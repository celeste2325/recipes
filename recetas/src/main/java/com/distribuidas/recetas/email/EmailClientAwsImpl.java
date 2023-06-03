package com.distribuidas.recetas.email;

import com.distribuidas.recetas.email.template.TemplateManager;
import org.springframework.stereotype.Component;

@Component
public class EmailClientAwsImpl implements EmailClient {

    final private TemplateManager templateManager;

    final private AwsSesClient awsSesClient;

    public EmailClientAwsImpl(TemplateManager templateManager, AwsSesClient awsSesClient) {

        this.templateManager = templateManager;
        this.awsSesClient= awsSesClient;
    }

    @Override
    public void ForgotPassword(String code, String email) {
        var templateString = this.templateManager.GetForgotpasswordTemplate();

        templateString =  templateString.replace("{URL}","localhost:8080");
        templateString =  templateString.replace("{code}",code);
        templateString =  templateString.replace("{email}",email);

        var paramenters = new EmailParamenters();
        paramenters.setSenderEmail("example.uai@grupo1.com");
        paramenters.setSubject("reinicia tu password");
        paramenters.setRecipientEmail(email);
        paramenters.setHtmlBody(templateString);

        awsSesClient.SendEmail(paramenters);

    }

    @Override
    public void NewRegister(String code, String email) {
        var templateString = this.templateManager.GetNewRegisterTemplate();
        templateString =  templateString.replace("{URL}","localhost:8080");
        templateString =  templateString.replace("{code}",code);
        templateString =  templateString.replace("{email}",email);

        var paramenters = new EmailParamenters();
        paramenters.setSenderEmail("example.uai@grupo1.com");
        paramenters.setSubject("Confirma tu cuenta");
        paramenters.setRecipientEmail(email);
        paramenters.setHtmlBody(templateString);

        awsSesClient.SendEmail(paramenters);
    }
}
