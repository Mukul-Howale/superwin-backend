package com.superwin.superWin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SuperWinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperWinApplication.class, args);
	}

}
