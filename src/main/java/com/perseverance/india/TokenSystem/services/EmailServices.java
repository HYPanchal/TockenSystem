package com.perseverance.india.TokenSystem.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailServices {
	
	public static final String username = "panchalrushikesh9890";
	public static final String password = "okjyzobobbvcxnem";
	public static final String emailFrom = "panchalrushikesh9890@gmail.com";
	public static final String emailTo = "panchalrushikesh74@gmail.com";
	public static final String emailSubject = "TokenSystem";
	
	private void mailUtility(String emailbody) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			message.setFrom(new InternetAddress(emailFrom));
			message.setSubject(emailSubject);
			message.setText(emailbody);

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void message(){
		String message = "Dear User,\n"+
						 "         TokenSystem Application has been restarted with lastes update at Date:- " + this.date() + " and Time:- " + this.time() +
						 "\n\n\nRegards,\nPerseverance Developement Team";
		this.mailUtility(message);
	}
	
	private String date(){
		Date currentSystemDate = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		String formatedDate = dateFormat.format(currentSystemDate);
		
		return formatedDate;
	}
	
	private String time() {
		Date currentSystemTime = new Date();
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		
		String formatedTime = timeFormat.format(currentSystemTime);
		
		return formatedTime;
	}
}