package com.gestione.blogging.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(String to, String subject, String body) throws MessagingException {
		if(body==null) body = "mail di default";
		MimeMessage message = mailSender.createMimeMessage();
		message.setSubject(subject,"UTF-8");
		message.setContent(body, "text/html; charset=UTF-8");

		MimeMessageHelper helper = new MimeMessageHelper(message);


		helper.setTo(to);


		mailSender.send(message);
		System.out.println("Email inviata con successo a " + to);
	}
}
