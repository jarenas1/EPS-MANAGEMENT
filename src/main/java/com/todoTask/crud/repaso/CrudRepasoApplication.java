package com.todoTask.crud.repaso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrudRepasoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRepasoApplication.class, args);
	}

}
