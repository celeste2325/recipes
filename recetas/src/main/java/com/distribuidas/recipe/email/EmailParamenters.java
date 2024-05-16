package com.distribuidas.recipe.email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailParamenters {

    private String senderEmail; //= "sender@example.com";
    private String recipientEmail; //= "recipient@example.com";
    private String subject; //= "This is the subject line.";
    private String htmlBody;// = "<h1>Hello!</h1><p>This is the email body in HTML format.</p>";
}
