package com.superwin.referrralservice;

import org.springframework.boot.SpringApplication;

public class TestReferrralServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReferrralServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
