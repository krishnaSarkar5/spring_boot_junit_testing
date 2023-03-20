package com.junitTutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.junitTutorial.controller.EmployeeController;
import com.junitTutorial.entity.Employee;
import com.junitTutorial.repository.EmployeeRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
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

    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
}
