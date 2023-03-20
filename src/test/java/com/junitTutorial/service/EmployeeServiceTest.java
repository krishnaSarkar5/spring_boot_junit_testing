package com.junitTutorial.service;

import com.junitTutorial.entity.Employee;
import com.junitTutorial.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ComponentScan({"com.junitTutorial.service","com.junitTutorial.repository"})
public class EmployeeServiceTest {



    @Autowired
    private EmployeeService employeeService ;

    @MockBean
    private EmployeeRepository employeeRepository;

    Employee emp1 = new Employee(1l,"Krishna Sarkar","9878527419");
    Employee emp2 = new Employee(2l,"Rahul Sen","9586247851");
    Employee emp3 = new Employee(3l,"Suresh Saha","9575415326");

    @Test
    public void getEmployeesTest(){

        List employees = new ArrayList();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        when(employeeRepository.findAll()).thenReturn(employees);
        assertEquals(3,employeeService.getAllEmployees().size());
    }

    @Test
    public void getEmployeeByIdTest(){

        Employee emp = new Employee(5l,"Sunil Saha","9658741258");
        when(employeeRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        assertEquals(emp,employeeService.getEmployeeById(emp.getId()));
    }

    @Test
    public void saveEmployeeTest(){

        Employee emp = new Employee(5l,"Sunil Saha","9658741258");
        when(employeeRepository.save(emp)).thenReturn(emp);
        assertEquals(emp,employeeService.createEmployee(emp));
    }

    @Test
    public void deleteEmployeeTest(){
        Employee emp = new Employee(9l,"Manish Sen","9874856925");
        employeeService.deleteEmployee(emp.getId());
        verify(employeeRepository,times(1)).deleteById(emp.getId());
    }
}
