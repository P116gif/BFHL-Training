package com.example.day5;

import org.springframework.boot.SpringApplication;

public class TestDay5Application {

	public static void main(String[] args) {
		SpringApplication.from(Day5Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
