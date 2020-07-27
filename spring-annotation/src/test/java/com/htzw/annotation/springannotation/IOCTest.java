package com.htzw.annotation.springannotation;



import com.htzw.annotation.bean.Person;
import com.htzw.annotation.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;



public class IOCTest {

	@SuppressWarnings("resource")
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] definitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : definitionNames) {
			System.out.println(name);
		}
		System.out.println("=================");
		Person person = applicationContext.getBean(Person.class);
		System.out.println(person.toString());
	}
}
