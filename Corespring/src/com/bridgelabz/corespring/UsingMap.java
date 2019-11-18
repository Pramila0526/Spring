package com.bridgelabz.corespring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgelabz.model.Mapmodel;

public class UsingMap {

	public static void main(String[] args) {
	
ApplicationContext context=new ClassPathXmlApplicationContext("map.xml");
Mapmodel m=(Mapmodel) context.getBean("s");
m.show();

	}

}
