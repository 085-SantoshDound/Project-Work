package com.app.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {


	@Autowired
	private JavaMailSender sender;
	
	
	
	public EmailService()
	{
		System.out.println("Email Service ");
	}

	public void sendSimpleEmail(String toEmail,String body, String subject) throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
		
	
		message.setFrom("dronegalagycdac@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		System.out.println(toEmail);
		System.out.println(body);
		System.out.println(subject);
		
			sender.send(message);
		
		System.out.println("Mail Send...");
	}
		
	
}
