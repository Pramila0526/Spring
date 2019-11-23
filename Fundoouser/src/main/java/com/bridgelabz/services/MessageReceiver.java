package com.bridgelabz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bridgelabz.model.Rabbitmqmodel;

public class MessageReceiver {
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * purpose: this method will take a rabbitMQ body as input,and sends mail by
	 * 			fetching all details from that body
	 * 
	 * @param body RabbitMQBody
	 */
	public void sendMessage(Rabbitmqmodel body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(body.getEmail());
		message.setText(body.getBody());
		message.setSubject(body.getSubject());
		mailSender.send(message);

	}

}
