package com.poly.service;

import javax.mail.MessagingException;

import com.poly.entity.MailInfo;

public interface MailerService {
	//dk
	void send(MailInfo mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInfo mail);
	void queue(String to, String subject, String body);
	
	
}
