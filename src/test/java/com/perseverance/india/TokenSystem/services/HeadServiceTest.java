package com.perseverance.india.TokenSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.model.Operaters;
import com.perseverance.india.TokenSystem.repository.Mongo.EntityRepoMongo;

/*
 * This class is a test class contain all the test cases related to HeadService class.
 * @author :- Hrushikesh
 * @dateCreated :- 23-12-23
 */
class HeadServiceTest {
	// Injecting Mock of HeadService class as headService by using @InjectMock
	// annotation
	@InjectMocks
	private HeadService headService;
	// Creating mock instance of EmployeeRepo interface as employeeRepo by using
	// @Mock annotation.
	@Mock
	private EntityRepoMongo entityRepoMongo;
	// Creating mock instance of Logger as logger by using @Mock annotation
	@Mock
	private Logger logger;
	// Creating mock instance of CrudServices as crudServices by using @Mock
	// annotation.
	@Mock
	private CrudServices crudServices;

	@Mock
	private Employee employee;

	// Injecting Mock of ObjectValidator class as objectValidator by using @Mock
	// annotation
	@Mock
	private ObjectValidator objectValidator;

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
	 * This method is implemented for successful testing
	 * headServiceTestSaveEmp() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 23-12-23
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestSaveEmp() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Creating operation variable of type String for testing purpose.
		String operation = "save_emp";
		// Mocking toOperaters() of ObjectValidator class for returning SAVE_EMP enum.
		when(objectValidator.toOperaters(operation)).thenReturn(Operaters.SAVE_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning employee.
		when(objectValidator.tokenSysValidator(employee)).thenReturn(employee);
		// Mocking returnFlag() of ObjectValidator class for returning true.
		when(objectValidator.returnFlag()).thenReturn(true);
		// Mocking saveEmp() of CrudService class for returning employee object.
		when(crudServices.saveEmp(employee)).thenReturn(employee);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, operation);
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertNull(empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(0, empResult.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing
	 * headServiceTestOperationNotFound() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-23
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestOperationNotFound() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Creating operation variable of type String for testing purpose.
		String operation = "save_emp";
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, operation);
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertNotNull(empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(400, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for Failure testing
	 * headServiceTestForSaveEmp_EmployeeExist() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-23
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestForSaveEmp_EmployeeExist() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Creating operation variable of type String for testing purpose.
		String operation = "save_emp";
		// Mocking toOperaters() of ObjectValidator class for returning SAVE_EMP enum.
		when(objectValidator.toOperaters(operation)).thenReturn(Operaters.SAVE_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning Employee object.
		when(objectValidator.tokenSysValidator(employee)).thenReturn(employee);
		// Mocking getEmpById() of ObjectValidator class for returning Employee object.
		when(crudServices.getEmpById("1")).thenReturn(employee);
		// Mocking returnFlag() of ObjectValidator class for returning true.
		when(objectValidator.returnFlag()).thenReturn(true);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, operation);
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertEquals("Employee Already Exist", employee.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(404, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestSaveEmp_EmpInvaid() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 30-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestSaveEmp_EmpInvaid() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Creating operation variable of type String for testing purpose.
		String operation = "save_emp";
		// Mocking toOperaters() of ObjectValidator class for returning SAVE_EMP enum.
		when(objectValidator.toOperaters(operation)).thenReturn(Operaters.SAVE_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning Employee Object.
		when(objectValidator.tokenSysValidator(employee)).thenReturn(employee);
		// Mocking returnFlag() of ObjectValidator class for returning false
		when(objectValidator.returnFlag()).thenReturn(false);
		// Mocking getEmpById() of CrudServices class for returning null.
		when(crudServices.getEmpById("1")).thenReturn(null);
		// Mocking saveEmp() of CrudServices class for returning employee.
		when(crudServices.saveEmp(employee)).thenReturn(employee);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, operation);
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertNull(empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(0, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestEditEmpForEmpDoseNotExist() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 30-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestEditEmpForEmpDoseNotExist() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Mocking toOperaters() of ObjectValidator class for returning EDIT_EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.EDIT_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning employee.
		when(objectValidator.tokenSysValidator(employee)).thenReturn(employee);
		// Mocking returnFalg() of ObjectValidator class for returning true.
		when(objectValidator.returnFlag()).thenReturn(true);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, anyString());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employee.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertEquals("Employee Dose Not Exist", empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(404, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestEditEmployee() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestEditEmployee() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Creating object of Employee for testing purpose.
		Employee employeeUpdate = new Employee("Rushi", 1234, "MG");
		// Setting employeeId to 1.
		employeeUpdate.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning EDIT_EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.EDIT_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning employee.
		when(objectValidator.tokenSysValidator(employeeUpdate)).thenReturn(employeeUpdate);
		// Mocking returnFalg() of ObjectValidator class for returning true.
		when(objectValidator.returnFlag()).thenReturn(true);
		// Mocking getEmpById() of CrudServices class for returning Employee object.
		when(crudServices.getEmpById("1")).thenReturn(employee);
		// Mocking editEmp() of CrudServices class for returning Employee object
		when(crudServices.editEmp(employeeUpdate)).thenReturn(employeeUpdate);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employeeUpdate, anyString());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertNull(empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(0, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestEditEmployeeNotValid() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestEditEmployeeNotValid() {
		// Creating object of Employee for testing purpose.
		Employee employeeUpdate = new Employee("Rushi", 1234, "MG");
		// Setting employeeId to 1.
		employeeUpdate.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning EDIT_EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.EDIT_EMP);
		// Mocking tokenSysValidator() of ObjectValidator class for returning employee.
		when(objectValidator.tokenSysValidator(employeeUpdate)).thenReturn(employeeUpdate);
		// Mocking returnFalg() of ObjectValidator class for returning true.
		when(objectValidator.returnFlag()).thenReturn(false);
		// Mocking getEmpById() of CrudServices class for returning Employee object.
		when(crudServices.getEmpById("1")).thenReturn(employee);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employeeUpdate, anyString());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeeName(), empResult.getEmployeeName());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeePin(), empResult.getEmployeePin());
		// Checking expected output and actual result.
		assertEquals(employeeUpdate.getEmployeeType(), empResult.getEmployeeType());
		// Checking expected output and actual result.
		assertNull(empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(0, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestDeleteEmployee() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestDeleteEmployee() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning DELETE_EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.DELETE_EMP);
		// Mocking getEmpById() of CrudServices class for returning Employee object.
		when(crudServices.getEmpById("1")).thenReturn(employee);
		// Mocking deleteEmp() of CrudServices class for returning Employee object
		when(crudServices.deleteEmp(employee)).thenReturn(employee);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, anyString());
		// Checking expected output and actual result.
		assertEquals(employee, empResult);
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestDeleteEmployeeDoseNotExist() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 30-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestDeleteEmployeeDoseNotExist() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning DELETE_EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.DELETE_EMP);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, anyString());
		// Checking expected output and actual result.
		assertEquals(employee, empResult);
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestGetEmployee() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestGetEmployee() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.EMP);
		// Mocking getEmpById() of CrudServices class for returning Employee object.
		when(crudServices.getEmpById("1")).thenReturn(employee);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, anyString());
		// Checking expected output and actual result.
		assertEquals(employee, empResult);
	}
	
	/*
	 * This method is implemented for successful testing
	 * headServiceTestGetEmployeeDoseNotExist() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 30-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void headServiceTestGetEmployeeDoseNotExist() {
		// Creating object of Employee for testing purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Setting employeeId to 1.
		employee.setEmployeeId("1");
		// Mocking toOperaters() of ObjectValidator class for returning EMP enum.
		when(objectValidator.toOperaters(anyString())).thenReturn(Operaters.EMP);
		// Calling headService() of HeadService class.
		Employee empResult =  headService.headService(employee, anyString());
		// Checking expected output and actual result.
		assertEquals("Employee Dose Not Exist", empResult.getErrorDescription());
		// Checking expected output and actual result.
		assertEquals(404, empResult.getStatusCod());
	}
	
	/*
	 * This method is implemented for successful testing
	 * empListTest() of HeadServices class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 29-12-24
	 * 
	 * @lastUpdated :- 29-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void empListTest() {
		// Creating List of Employee Object for testing purpose.
		List<Employee> empList = new ArrayList<>();
		// Adding Object of employee to the list.
		empList.add(new Employee("Test", 1234, "Test"));
		// Adding Object of employee to the list.
		empList.add(new Employee("test", 1222, "test"));
		//Mocking findAll() of EntityRepoMonogo interface for returning empList.
		when(crudServices.getAllEmp()).thenReturn(empList);
		//Calling getAllEmp() of CrudServices class and storing it into empResultList.
		List<Employee> empResultList = headService.empList();
		//Verifying weather findAll() of EntityReposMongo interface has been called or not.
		verify(crudServices).getAllEmp();
		// Checking the expected output and actual result is equla.
		assertEquals(empList, empResultList);
	}
}