package com.perseverance.india.TokenSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.repository.Mongo.EntityRepoMongo;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is a test class contain all the test cases related to CrudServices class.
 * @author :- Hrushikesh
 * @dateCreated :- 23-12-23
 */
class CrudServicesTest {
	// Injecting Mock of CrudService class as crudService by using @InjectMock
	// annotation
	@InjectMocks
	private CrudServices crudServices;
	// Creating mock instance of EmployeeRepo interface as employeeRepo by using
	// @Mock annotation.
	@Mock
	private EntityRepoMongo entityRepoMongo;

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
	 * @lateUpdate :- 29-12-23
	 */
	@BeforeEach
	void setup() {
		// This method call in Mockito that initializes annotated fields in
		// the provided test class with mock objects
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * This method is implemented for successful testing saveEmpTestSuccess() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 16-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void saveEmpTestSuccess() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("Test", 1234, "Test");
		// Call the saveEmp() of CrudService class and storing it in object of Employee.
		Employee savedEmployee = crudServices.saveEmp(employee);
		// Checking the expected output with actual result.
		assertEquals(employee, savedEmployee);
		// verifying that the save() of EntityRepoMongo is been call or not.
		verify(entityRepoMongo).save(employee);
	}

	/*
	 * This method is implemented for successful testing saveEmpTestFail() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 16-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void saveEmpTestFail() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("Test", 1234, "test");
		// Simulating RuntimeException by using doThrow() when save() of EntityRepoMongo
		// class.
		doThrow(new RuntimeException("Simulated Exception")).when(entityRepoMongo).save(employee);
		// Calling saveEmp() of CrudServices class and storing it into Employee object.
		Employee savedEmployee = crudServices.saveEmp(employee);
		// Checking weather savedEmployee is null or not.
		assertNull(savedEmployee);
		// verifying that the save() of EmployeeRepo class
		verify(entityRepoMongo).save(employee);
	}

	/*
	 * This method is implemented for successful testing editEmpTestSuccess() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestSuccess() {
		// Creating object of Employee, representing employee form Data Base.
		Employee employeeFromDB = new Employee("Test", 1234, "test");
		// Creating object of Employee, representing employee form API.
		Employee employeeFromAPI = new Employee("Rushi", 1233, "MG");
		// Setting employeeId to "1h543h5h3h"
		employeeFromAPI.setEmployeeId("1h543h5h3h");
		// Setting status code to 200.
		employeeFromAPI.setStatusCod(200);
		// Mocking findById() of EmployeeRepo interface for returning object of
		// Employee.
		Mockito.when(entityRepoMongo.findBy_id(employeeFromAPI.getEmployeeId())).thenReturn(employeeFromDB);
		// Calling editEmp() CrudServices class and storing it in Employee object.
		Employee employeeResult = crudServices.editEmp(employeeFromAPI);
		// Verifying weather findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employeeFromAPI.getEmployeeId());
		// Verifying weather save() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).save(ArgumentMatchers.any(Employee.class));
		// Checking the expected output is not null.
		assertNotNull(employeeResult);
		// Checking the expected name and actual name is equal.
		assertEquals("Rushi", employeeFromAPI.getEmployeeName());
		// Checking the expected pin and actual pin is equal.
		assertEquals(1233, employeeFromAPI.getEmployeePin());
		// Checking the expected type and actual type is equal.
		assertEquals("MG", employeeFromAPI.getEmployeeType());
		// Checking the expected status code and actual status code is equal.
		assertEquals(200, employeeFromAPI.getStatusCod());
	}

	/*
	 * This method is implemented for successful testing editEmpTestFail() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestFail() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("Test", 1234, "Test");
		// Setting employeeId to "1h543h5h3h"
		employee.setEmployeeId("1h543h5h3h");
		// Mocking findById() EntityRepoMongo interface for returning empty object of
		// Employee.
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId())).thenReturn(employee);
		// Mocking save() of EntityrepoMongo interface for throwing RuntimeException.
		Mockito.doThrow(new RuntimeException("Simulated Exception")).when(entityRepoMongo).save(employee);
		// Calling editEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.editEmp(employee);
		// Verifying weather the findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Verifying weather the save() if EntityRepoMongo is been called or not
		verify(entityRepoMongo).save(employee);
		// Checking weather the empResult is null or not.
		assertNull(empResult);
	}

	/*
	 * This method is implemented for successful testing editEmpTestForNameNotNull()
	 * of CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 26-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestForNameNotNull() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 0, null);
		// Mocking findById() EntityRepoMongo interface for returning object of Employee.
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId())).thenReturn(employee);
		// Calling editEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.editEmp(employee);
		// Verifying weather the findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
	}

	/*
	 * This method is implemented for successful testing editEmpTestForPinNotNull()
	 * of CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 26-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestForPinNotNull() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee(null, 1234, null);
		// Mocking findById() EntityRepoMongo interface for returning empty object of
		// Employee.
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId())).thenReturn(employee);
		// Calling editEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.editEmp(employee);
		// Verifying weather the findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
	}

	/*
	 * This method is implemented for successful testing
	 * editEmpTestForEmpTypeNotNull() of CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 26-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestForEmpTypeNotNull() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee(null, 0, "test");
		// Mocking findById() EntityRepoMongo interface for returning empty object of
		// Employee
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId())).thenReturn(employee);
		// Calling editEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.editEmp(employee);
		// Verifying weather the findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
	}

	/*
	 * This method is implemented for successful testing editEmpTestForException()
	 * of CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 26-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void editEmpTestForException() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("Rushi", 0, "MG");
		// Mocking findById() of EntityRepoMongo interface for Throwing simulated
		// RuntimeException.
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId()))
				.thenThrow(new RuntimeException("Simulated Exception"));
		// Calling editEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.editEmp(employee);
		// Verifying weather the findBy_id() of EntityRepoMongo is been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Checking weather the empResult is null or not.
		assertNull(empResult);
	}

	/*
	 * This method is implemented for successful testing deleteEmpTestSuccess() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void deleteEmpTestSuccess() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Mocking delete() EntityRepoMongo interface for doing nothing when called.
		Mockito.doNothing().when(entityRepoMongo).delete(employee);
		// Calling deleteEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.deleteEmp(employee);
		// Verifying weather the delete() of EntityRepoMongo has been called or not.
		verify(entityRepoMongo).delete(employee);
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking the expected output with actual result.
		assertEquals(employee.getStatusCod(), empResult.getStatusCod());
	}

	/*
	 * This method is implemented for successful testing deleteEmpTestFail() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void deleteEmpTestFail() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("Rushi", 1234, "MG");
		// Mocking delete() of EntityRepoMongo interface for Throwing simulated
		// RuntimeException.
		Mockito.doThrow(new RuntimeException("Simulation Exception")).when(entityRepoMongo).delete(employee);
		// Calling deleteEmp() of CrudService class and storing it in Employee object.
		Employee empResult = crudServices.deleteEmp(employee);
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking the expected output with actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking the expected output with actual result.
		assertEquals(empResult.getErrorDescription(), "Employee not deleted");
		// Checking the expected output with actual result.
		assertEquals(empResult.getStatusCod(), 202);
	}
	
	/*
	 * This method is implemented for successful testing getEmpById() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 27-01-24
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void getEmpByIdTestSuccess() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to "n4j5n4k54k5".
		employee.setEmployeeId("n4j5n4k54k5");
		// Setting status code to 200.
		employee.setStatusCod(200);
		// Mocking findById() EntityRepoMongo interface for returning empty object of
		// Employee
		Mockito.when(entityRepoMongo.findBy_id(employee.getEmployeeId())).thenReturn(employee);
		// Calling getEmpById() of CrudServices class and setting it empResult.
		Employee empResult = crudServices.getEmpById(employee.getEmployeeId());
		// Verifying findBy_id() of EntityRepoMongo has been called or not.
		verify(entityRepoMongo).findBy_id(employee.getEmployeeId());
		// Checking the expected output with actual result.
		assertEquals(employee, empResult);
	}
	
	/*
	 * This method is implemented for failure testing getEmpById() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 27-01-24
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void getEmpByIdTestFail() {
		// Creating employeeId variable of type String for testing purpose.
		String employeeId = "n4j5n4k54k5";
		// Mocking findById() EntityRepoMongo interface for returning empty object of
		// Employee
		Mockito.doThrow(new RuntimeException("Simulate Exception")).when(entityRepoMongo).findBy_id(employeeId);
		// Calling getEmpById() of CrudServices class and setting it empResult.
		Employee empResult = crudServices.getEmpById(employeeId);
		// Verifying findBy_id() of EntityRepoMongo has been called or not.
		verify(entityRepoMongo).findBy_id(employeeId);
		// Checking the actual result is null or not.
		assertNull(empResult);
	}
	
	/*
	 * This method is implemented for Successful testing getAllEmp() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 27-01-24
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void getAllEmpTestSuccess() {
		// Creating empList variable of type List<Employee> for testing purpose.
		List<Employee> empList = new ArrayList<>();
		// Adding Object of employee to the list.
		empList.add(new Employee("Test", 1234, "Test"));
		// Adding Object of employee to the list.
		empList.add(new Employee("test", 1222, "test"));
		//Mocking findAll() of EntityRepoMonogo interface for returning empList.
		Mockito.when(entityRepoMongo.findAll()).thenReturn(empList);
		//Calling getAllEmp() of CrudServices class and storing it into empResultList.
		List<Employee> empResultList = crudServices.getAllEmp();
		//Verifying weather findAll() of EntityReposMongo interface has been called or not.
		verify(entityRepoMongo).findAll();
		// Checking the expected output and actual result is equla.
		assertEquals(empList, empResultList);
	}
	
	/*
	 * This method is implemented for failure testing getAllEmp() of
	 * CrudServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 27-01-24
	 * 
	 * @lastUpdated :- 27-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void getAllEmpTestFail() {
		//Mocking findAll() of EntityRepoMonogo interface for returning empList.
		Mockito.doThrow(new RuntimeException("Simulated Exception")).when(entityRepoMongo).findAll();
		//Calling getAllEmp() of CrudServices class and storing it into empResultList.
		List<Employee> empResultList = crudServices.getAllEmp();
		//Verifying weather findAll() of EntityReposMongo interface has been called or not.
		verify(entityRepoMongo).findAll();
		// Checking the actual result is null or not.
		assertNull(empResultList);
	}
}