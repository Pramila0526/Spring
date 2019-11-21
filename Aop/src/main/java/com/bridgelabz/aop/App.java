package com.bridgelabz.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext context=new ClassPathXmlApplicationContext("aop.xml");   //application context type of  IOC container
       Student s=(Student) context.getBean("student");
       s.getName();
       s.getAge();
       s.printthrowexception();
    }
}
