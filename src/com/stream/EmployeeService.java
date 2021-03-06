package com.stream;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {
	private EmployeeRepository repository;

	public EmployeeService() {
		repository = EmployeeRepository.getInstance();
		repository.setEmployeeData();
	}

	public double sumSalary() {
		return repository.getEmployeeData().stream().mapToDouble(employee -> employee.getSalary()).sum();
	}

	public void employeeDuration() {
		repository.getEmployeeData().stream().forEach((employee) -> {
			LocalDate currentDate = LocalDate.now();
			int years = Math.abs(currentDate.getYear() - employee.getHireDate().getYear());
			int months = Math.abs(currentDate.getMonthValue() - employee.getHireDate().getMonthValue());
			int days = Math.abs(currentDate.getDayOfMonth() - employee.getHireDate().getDayOfMonth());
			System.out.println(employee.getFirstName() + " " + employee.getLastName() + " is from : " + years
					+ " years " + months + " months " + days + " days");
		});
	}
	
	public void employeeWithNoDepartment() {
		repository.getEmployeeData().stream().forEach((employee)->{
			if(employee.getDepartment() == null) {
				System.out.println(employee.getFirstName() + " "+ employee.getLastName() + " not assigned Department");
			}
		});
	}
	
	public void increaseSalary() {
		repository.getEmployeeData().stream().forEach((employee)->{
			double salary = employee.getSalary() + employee.getSalary()*0.15;
			System.out.println(employee.getFirstName() + " " + employee.getLastName() + " : "+salary);
		});;
	}
	
	public void getDayOfWeekOfEmployee() {
		repository.getEmployeeData().stream().forEach((employee)->{
			 System.out.println(employee.getFirstName() + " " + 
						employee.getLastName() + " joined on " + employee.getHireDate().getDayOfWeek());
		});
	}
	
	public void getHighestCountDeparment() {
	
		Map<Department, Long> depMap = repository.getEmployeeData().stream().
				collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
		System.out.println( depMap.entrySet().stream().
				max((entry1,entry2) -> entry1.getValue() > entry2.getValue()?1:-1).toString());
	}

}
