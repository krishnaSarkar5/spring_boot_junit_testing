package com.junitTutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.junitTutorial.controller.EmployeeController;
import com.junitTutorial.entity.Employee;
import com.junitTutorial.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import java.awt.*;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private ObjectWriter objectWriter = objectMapper.writer();


    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    Employee emp1 = new Employee(1l,"Krishna Sarkar","9878527419");
    Employee emp2 = new Employee(2l,"Rahul Sen","9586247851");
    Employee emp3 = new Employee(3l,"Suresh Saha","9575415326");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllEmployeesTest() throws Exception{
        List<Employee> employees= List.of(emp1,emp2,emp3);
        when(employeeRepository.findAll()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].name",is("Rahul Sen")));
    }


}
