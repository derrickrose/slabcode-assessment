package com.slabcode.assessment.service.remote;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.String.format;

@Service
public class SendgridService {

    @Value("${notification.email.send-grid.secret-key}")
    private String secretKey;

    @Value("${notification.email.send-grid.validated-single-sender}")
    private String sender;

    public void notifyUser(String userName, String passWord, String email) {

        Email from = new Email(sender);
        String subject = "SLABCODE assessment account creation notification.";
        Email to = new Email(email);
        Content content = new Content("text/plain", format("Your account have been created with the username %s and password %s", userName, passWord));
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(secretKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}