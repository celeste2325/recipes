package com.distribuidas.recetas.email;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Component
public class AwsSesClient {
    SesClient client = SesClient.builder().region(Region.US_EAST_1).build();
    private final String senderEmail = "no-reply@adicook.com";
    public void SendEmail(EmailParamenters parameters ) {


        try {
            SendEmailRequest request = SendEmailRequest.builder()
                    .destination(Destination.builder().toAddresses(parameters.getRecipientEmail()).build())
                    .message(Message.builder().body(Body.builder()
                    .html(Content.builder().data(parameters.getHtmlBody()).charset("UTF-8").build()).build())
                    .subject(Content.builder().data(parameters.getSubject()).charset("UTF-8").build()).build())
                    .source(senderEmail)
                    .build();

            SendEmailResponse response = client.sendEmail(request);
            System.out.println("Email sent! Message ID: " + response.messageId());

        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
