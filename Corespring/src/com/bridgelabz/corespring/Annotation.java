package com.bridgelabz.corespring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgelabz.model.Annotationmodel;
import com.bridgelabz.model.Appconfig;

public class Annotation {

	public static void main(String[] args) {
		
		
		AbstractApplicationContext context=new AnnotationConfigApplicationContext( Appconfig.class);
		Annotationmodel am=(Annotationmodel) context.getBean("e");
		
		

	}

}
