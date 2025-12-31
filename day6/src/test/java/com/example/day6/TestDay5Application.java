package com.example.day6;

import org.springframework.boot.SpringApplication;

public class TestDay5Application {

	public static void main(String[] args) {
		SpringApplication.from(Day6Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
