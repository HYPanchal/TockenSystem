package com.perseverance.india.TokenSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.model.Operaters;

/*
 * This class is a test class contain all the test cases related to HeadService class.
 * @author :- Hrushikesh
 * @dateCreated :- 04-01-24
 */
class ObjectValidatorTest {
	/*
	 * This method is implemented for successful testing tokenSysValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 28-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void tokenSysValidatorTestSuccess() {
		// Creating Object of Employee for test purpose.
		Employee employee = new Employee("test", 1234, "test");
		// Creating Object of ObjectValidator class for test purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Calling tokenSysValidator() of ObjectValidator class and storing the return
		// in Employee object
		Employee resutlEmp = objectValidator.tokenSysValidator(employee);
		// Checking the errorDescription of employee is null or not
		assertNull(resutlEmp.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(200, resutlEmp.getStatusCod());
		// Checking ObjectValidator's flag is true or not.
		assertTrue(objectValidator.returnFlag());
	}

	/*
	 * This method is implemented for Failure testing tokenSysValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 28-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void tokenSysValidatorTestFail() {
		// Creating Object of Employee for test purpose.
		Employee employee = new Employee("", 123, "");
		// Creating Object of ObjectValidator class for test purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Calling tokenSysValidator() of ObjectValidator class and storing the return
		// in Employee object
		Employee resutlEmp = objectValidator.tokenSysValidator(employee);
		// Checking the expected output with actual result.
		assertEquals(resutlEmp, employee);
		// Checking the errorDescription of employee is null or not
		assertNotNull(resutlEmp.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, resutlEmp.getStatusCod());
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
	}
	
	/*
	 * This method is implemented for Successful testing toOperaters() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 28-01-24
	 * 
	 * @lastUpdated :- 28-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void toOperatersTestSuccess() {
		// Creating operation variable of type String for testing purpose.
		String operation = "save_emp";
		// Creating Object of ObjectValidator class for test purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Calling toOperaters() of ObjectValidator class and storing it into opeResult variable.
		Operaters opeResult = objectValidator.toOperaters(operation);
		// Checking weather the opeResult is null or not.
		assertNotNull(opeResult);
	}
	
	/*
	 * This method is implemented for Failure testing toOperaters() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 28-01-24
	 * 
	 * @lastUpdated :- 28-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void toOperatersTestFail() {
		// Creating operation variable of type String for testing purpose.
		String operation = "save_em";
		// Creating Object of ObjectValidator class for test purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Calling toOperaters() of ObjectValidator class and storing it into opeResult variable.
		Operaters opeResult = objectValidator.toOperaters(operation);
		// Checking weather the opeResult is null or not.
		assertNull(opeResult);
	}

	/*
	 * This method is implemented for Failure testing employeeNameValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeeNameValidatorTestForEmptyName() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing name of employee.
		String name = "";
		// Making duplicate dummy method of employeeNameValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeNameValidator", String.class);
		// Setting access to the employeeNameValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeNameValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, name);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Name, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing employeeNameValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeeNameValidatorTestForAlphaNumericName() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing name of employee.
		String name = "1234";
		// Making duplicate dummy method of employeeNameValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeNameValidator", String.class);
		// Setting access to the employeeNameValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeNameValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, name);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Name, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}
	
	/*
	 * This method is implemented for Failure testing employeeNameValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeeNameValidatorTestForNullName() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing name of employee.
		String name = null;
		// Making duplicate dummy method of employeeNameValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeNameValidator", String.class);
		// Setting access to the employeeNameValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeNameValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, name);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Name, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing employeePinValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeePinValidatorTestForPinCount() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing pin of employee.
		int pin = 123;
		// Making duplicate dummy method of employeePinValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeePinValidator", int.class);
		// Setting access to the employeePinValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeePinValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, pin);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Pin, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing employeePinValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeePinValidatorTestForPinZero() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing pin of employee.
		int pin = 0;
		// Making duplicate dummy method of employeePinValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeePinValidator", int.class);
		// Setting access to the employeePinValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeePinValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, pin);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Pin, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing employeeTypeValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeeTypeValidatorTestForAlphaNumericType() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing type of employee.
		String type = "123";
		// Making duplicate dummy method of employeeTypeValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeTypeValidator", String.class);
		// Setting access to the employeeTypeValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeTypeValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, type);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Type, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}

	/*
	 * This method is implemented for Failure testing employeePinValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 04-01-24
	 * 
	 * @lastUpdated :- 08-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeetypeValidatorTestForEmptyType() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing type of employee.
		String type = "";
		// Making duplicate dummy method of employeeTypeValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeTypeValidator", String.class);
		// Setting access to the employeeTypeValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeTypeValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, type);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Type, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}
	
	/*
	 * This method is implemented for Failure testing employeePinValidator() of
	 * ObjectValidator class.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 28-01-24
	 * 
	 * @lastUpdated :- 28-01-24
	 */
	// @Test annotation says that the annotated method is a test method
	// that should be executed by the testing framework.
	@Test
	void employeetypeValidatorTestForNullType() {
		// Creating object of ObjectValidator class for testing purpose.
		ObjectValidator objectValidator = new ObjectValidator();
		// Creating temporary variable for storing type of employee.
		String type = null;
		// Making duplicate dummy method of employeeTypeValidator of ObjectValidator
		// class.
		Method privateMethod = ReflectionUtils.findMethod(ObjectValidator.class, "employeeTypeValidator", String.class);
		// Setting access to the employeeTypeValidator() of ObjectValidator to true.
		privateMethod.setAccessible(true);
		// Calling the dummy method of employeeTypeValidator().
		ReflectionUtils.invokeMethod(privateMethod, objectValidator, type);
		// Checking ObjectValidator's flag is false or not.
		assertFalse(objectValidator.returnFlag());
		// Checking the expected output with actual result.
		assertEquals("Invalid value/Format for Employee Type, ", objectValidator.employee.getErrorDescription());
		// Checking the expected output with actual result.
		assertEquals(400, objectValidator.employee.getStatusCod());
	}
}