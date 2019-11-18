package com.bridgelabz.aop;

public class Student {

	private Integer age;
	private String name;

	public Integer getAge() {
		System.out.println("Age  :" + age);
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		System.out.println("name : " + name);
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printthrowexception() {
		System.out.println("Exception raised..");
		throw new IllegalArgumentException();
	}

}
