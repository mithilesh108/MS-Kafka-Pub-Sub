package com.cts;

import com.cts.dto.EmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.controller.EmployeeController;

@SpringBootApplication
public class PublisherServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PublisherServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner run method");
		EmployeeController.listEmpDto.add(new EmployeeDTO(6, "mk", "bihar", 10000));
		EmployeeController.listEmpDto.add(new EmployeeDTO(7, "mohan", "delhi", 70000));
		EmployeeController.listEmpDto.add(new EmployeeDTO(8, "lakhan", "hyd", 40000));
		EmployeeController.listEmpDto.add(new EmployeeDTO(9, "sita", "up", 15000));
		EmployeeController.listEmpDto.add(EmployeeDTO.builder().id(5).name("gita").address("up").salary(20000).build());
	}

}
