package com.example.demo_employee.service.implementation;

import com.example.demo_employee.dto.EmployeeDto;
import com.example.demo_employee.entity.Employee;
import com.example.demo_employee.mapper.EmployeeMapper;
import com.example.demo_employee.repository.EmployeeRepository;
import com.example.demo_employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee employee1 =  employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employee1);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."Employee of id \{id} doesnot exits"));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return List.of(EmployeeMapper.mapToEmployeeDto((Employee) employeeRepository.findAll()));
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee =employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(STR."Employee of id \{employeeId} doesnot exits"));

        //set the employee using getters of employee dto
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

}
