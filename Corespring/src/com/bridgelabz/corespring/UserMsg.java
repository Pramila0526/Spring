package com.bridgelabz.corespring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.*;

import com.bridgelabz.model.Message;



public class UserMsg {

	public static void main(String[] args) {
	
		
		
	  Resource r=new ClassPathResource("msg.xml");
	  BeanFactory bf=new XmlBeanFactory(r);
	  
	  Message msg=(Message) bf.getBean("m");
	  msg.showmessage();
	  
	}

}
