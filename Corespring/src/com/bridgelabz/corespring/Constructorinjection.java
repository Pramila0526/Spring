	package com.bridgelabz.corespring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.*;

import com.bridgelabz.model.Constructormodel;



public class Constructorinjection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Resource r=new ClassPathResource("constructor.xml");
		BeanFactory bf=new XmlBeanFactory(r);
		Constructormodel c=(Constructormodel) bf.getBean("t");

	}

}
