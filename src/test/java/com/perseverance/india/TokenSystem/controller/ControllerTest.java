package com.perseverance.india.TokenSystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.perseverance.india.TokenSystem.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseverance.india.TokenSystem.services.HeadService;

/*
 * This class is a test class contain all the test cases related to Controller class.
 * @author :- Hrushikesh
 * @dateCreated :- 23-12-23
 */
/*@SpringBootTest annotation in the Spring Framework is used in the context of testing 
 * to indicate that a particular class is a Spring Boot test class.
 * This annotation is often used in combination with JUnit
 *  to create integration tests for Spring Boot applications. 
 */
@SpringBootTest
/*
 * @AutoConfigureMockMvc annotation in the Spring Framework is used in
 * conjunction with
 * 
 * @SpringBootTest to automatically configure and inject a MockMvc instance for
 * testing Spring MVC controllers.
 */
@AutoConfigureMockMvc
class ControllerTest {
	// Mocking the HeadService bean as headService by using @MockBean annotation.
	@MockBean
	private HeadService headService;
	// Injecting dependency of MockMvc class as mockMvc by using @Autowired
	// annotation.
	@Autowired
	private MockMvc mockMvc;
	// Injecting Mock of Controller class as controller by using @InjectMock
	// annotation
	@InjectMocks
	private Controller controller;
	// Injecting dependency of ObjectMapper as objectMapper by using @Autowired
	// annotation.
	@Autowired
	private ObjectMapper objectMapper;

	/*
	 * @BeforeEach annotation allows you to set up common test fixtures or perform
	 * any necessary initialization before each individual test is run.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- This is a void method.
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lateUpdate :- 27-12-23
	 */
	@BeforeEach
	void setup() {
		// This method call in Mockito that initializes annotated fields in
		// the provided test class with mock objects.
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * This method is implemented for successful testing crudOperation() of
	 * Controller class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-12-23
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void crudOperationsTestSuccess() throws Exception {
		// Setting base URl of API into baseUrl variable.
		String baseUrl = "/api/v1/emp/";
		String operation = "save_emp";
		// Created a object of employee type Employee for test purpose.
		Employee employee = new Employee("test", 1234, "test");
		//Mocking headService() of headService class for returning employee
		when(headService.headService(employee, operation)).thenReturn(employee);
		// This is part of an integration test using the MockMvc framework for testing
		// Spring MVC controllers.
		// Building HTTP POST request to a specific URL.
		mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + operation)
				// Setting the ContentType to JSON
				.contentType(MediaType.APPLICATION_JSON)
				// Setting the request body employee to JSON format.
				.content(objectMapper.writeValueAsString(employee))
				// Setting the Character Encoding to UTF-8
				.characterEncoding("utf-8"))
				// verifying that the HTTP response status is 200 OK.
				.andExpect(status().isOk()).andReturn();
		// Checking the expected result and actual result.
		assertEquals(employee, headService.headService(employee, operation));
	}

	/*
	 * This method is implemented for successful test of getEmpList() of Controller
	 * class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-12-23
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void getEmpListTestSuccess() throws Exception {
		// Creating List name employeeList of object type Employee for demo.
		List<Employee> employeeList = new ArrayList<>();
		// Adding 2 Test Object of Employee for test purpose.
		employeeList.add(new Employee("test_1", 1234, "test_1"));
		employeeList.add(new Employee("test_2", 1234, "test_2"));
		// This is part of an integration test using the MockMvc framework for testing
		// Spring MVC controllers.
		// Building HTTP POST request to a specific URL.
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/emp")
				// Setting the ContentType to JSON
				.contentType(MediaType.APPLICATION_JSON))
				// Verifying that the HTTP response status is 200 OK.
				.andExpect(MockMvcResultMatchers.status().isOk())
				// Verifying that the HTTP response content is of type JSON.
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				// Verifying that the HTTP response is a array.
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
	}
}