package com.bridgelabz.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.bridgelabz.model")
public class Appconfig {

	@Bean(name = "e")
	public Annotationmodel getAnnotationmodel() {
		Annotationmodel am = new Annotationmodel();
		am.setId(1);
		am.setName("pandit");
		am.setPhone(956131843);
		am.setAddress("aurangbad");
		return am;

	}
}
