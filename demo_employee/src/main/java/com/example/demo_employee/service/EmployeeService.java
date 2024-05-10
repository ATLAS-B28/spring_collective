package com.example.demo_employee.service;

import com.example.demo_employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long id);//employee dto here is the return type

    List<EmployeeDto> getAllEmployee();

    EmployeeDto updateEmployee(Long employeeId,EmployeeDto employeeDto);

    void deleteEmployee(Long id);
}
