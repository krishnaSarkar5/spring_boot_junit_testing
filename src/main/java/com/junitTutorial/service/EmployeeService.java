package com.junitTutorial.service;


import com.junitTutorial.entity.Employee;
import com.junitTutorial.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
       return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("No employee found with id "+id));
    }
    public Employee updateEmployee(Employee employee){
        Employee existedEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> new RuntimeException("No employee found with id " + employee.getId()));
        existedEmployee.setName(employee.getName());
        existedEmployee.setPhone(employee.getPhone());
        return employeeRepository.save(existedEmployee);
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }
}
