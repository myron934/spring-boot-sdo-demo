package com.sdo.spring_boot_demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.sdo.controller,com.sdo.configuration")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
