package com.superwin.referralservice;

import org.springframework.boot.SpringApplication;

public class TestReferralServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReferralServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
