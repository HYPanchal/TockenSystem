package com.perseverance.india.TokenSystem.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.services.HeadService;

/*
 * Controller class for handling requests related to user operations.
 * This class defines end points for retrieving and manipulating user data.
 * 
 * @author :- Hrushikesh
 * 
 * @dateCreate :- 03-12-23
 */
//@RestController annotation is applied to a class which is responsible for handling HTTP requests 
//and producing HTTP responses in a RESTful manner.
@RestController
//@RequestMapping annotation is used for establishing base URL for the entire class.
@RequestMapping("/api/v1")
public class Controller {
	// Injected dependence of HeadService class by using @Autowired annotation as
	// headService.
	@Autowired
	private HeadService headService;
	// Initialized static final variable of Logger as LOGGER. Logger is use to
	// record the runtime behavior of the application
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	/*
	 * This is post mapping API for saving, editing and deleting employee from the
	 * data base.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Request Body of type Employee expecting form the front
	 * end.
	 * 
	 * @param operation :- Variable of type String expecting from front end for
	 * deciding which operation should be perform. e.g:- saving, editing and
	 * deleting employee.
	 * 
	 * @return :- method does not return any thing. 
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 23-01-24
	 */
	// Mapping method with @PostMapping annotation with a specified URl
	// (/emp/{operation}).
	@PostMapping("/emp/{operation}")
	public ResponseEntity<Employee> crudOperations(@RequestBody Employee employee, @PathVariable String operation) {
		// Setting logger for starting the method.
		LOGGER.info("Controller -> crudeOperation() - method started.");
		// Calling the headService() of HeadService class and sending object of employee
		// and operation variable to the headService method
		return ResponseEntity.ok(headService.headservice(operation, employee));     
	}

	/*
	 * This is a get mapping API for retrieving all employees form the data base.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param :- No parameters required.
	 * 
	 * @return :- List of Employee objects.
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 26-12-23
	 */
	// Mapping method with @GetMapping annotation with a specified URl (/emp).
	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getEmployeeList(){
		// Setting logger for starting the method.
		LOGGER.info("Controller -> getEmpList() - method started");
		// Returning the List of Employee objects in the form of response.
		return ResponseEntity.ok(headService.empList());
	}
}