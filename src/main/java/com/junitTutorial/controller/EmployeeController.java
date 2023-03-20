package com.junitTutorial.controller;

import com.junitTutorial.entity.Employee;
import com.junitTutorial.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    @PostMapping("/create-employee")
    public Employee createEmployee(@RequestBody  Employee employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }


    @PostMapping("/update-employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.updateEmployee(employee);
        return employee1;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable  Long id){
        employeeService.deleteEmployee(id);
    }
}
