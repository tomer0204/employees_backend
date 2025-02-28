package com.example.employees_project.service;
import com.example.employees_project.Dto.EmployeeDto;
import com.example.employees_project.Exception.ResourceNotFoundException;
import com.example.employees_project.Mapper.EmployeeMapper;
import com.example.employees_project.entity.Employee;
import com.example.employees_project.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return  EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List <Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long employeeId) {
     Employee employee =  employeeRepository.findById(employeeId)
               .orElseThrow(() ->
                       new ResourceNotFoundException("Employee not found with id: " + employeeId)
               );

     employee.setFirstName(employeeDto.getFirstName());
     employee.setLastName(employeeDto.getLastName());
     employee.setEmail(employeeDto.getEmail());

     Employee updatedEmployee = employeeRepository.save(employee);
     return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId)
                );
        employeeRepository.deleteById(employeeId);
    }


}
