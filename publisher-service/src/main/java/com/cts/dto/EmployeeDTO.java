package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmployeeDTO {

	private int id;
	private String name;
	private String address;
	private double salary;
}
