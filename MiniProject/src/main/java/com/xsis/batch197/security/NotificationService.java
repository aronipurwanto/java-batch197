package com.xsis.batch197.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	private JavaMailSender mailSender;
	
	@Autowired
	public NotificationService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMailFotgotPassword(String email) throws MailException {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		UUID token = UUID.randomUUID();
		String url = "http://localhost:8097/reset-password/"+token.toString();
		String content = "Email ini adalah untuk reset password \n silahkan klik link berikut ini \n"+
				"<a href='"+ url +"'>Aktifasi Password </a>";
		mailMessage.setTo(email);
		mailMessage.setFrom("madtokim@gmail.com");
		mailMessage.setSubject("Forgot Password Mini Project");
		mailMessage.setText(content);
		
		mailSender.send(mailMessage);
	}

}
