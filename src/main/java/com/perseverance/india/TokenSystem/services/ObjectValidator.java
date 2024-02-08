package com.perseverance.india.TokenSystem.services;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.model.Operaters;

/*
* Service class responsible for handling operations related to employee validation.
* @author :- Hrushikesh
* @dateCreated :- 31-12-23
*/
//@Component annotation in Spring is used to indicate that a class is not service but act as a service class
@Component
public class ObjectValidator {
	//Created a common object of Employee for performing task throughout the validation.
	public Employee employee = new Employee();
	//boolean variable which will be sate as true if Employee object is in proper format.
	//and will be sate false if Employee is not in a proper format.
	public boolean flag;
	//String variable for error description.
	String errorDesc ="";
	// Initialized static final variable of Logger as LOGGER. Logger is use to
	// record the runtime behavior of the application.
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HeadService.class);

	/*
	 * This method is implemented for checking weather the Employee object is in a predefine format or not.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Request Body of type Employee expecting form the front
	 * end.
	 * 
	 * @return :- Object of type Employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 03-12-23
	 */
	public Employee tokenSysValidator(Employee employee) {
		//Setting the error Description of the Employee. 
		this.employee.setErrorDescription(null);
		//Setting the error Cod of the Employee. 
		this.employee.setStatusCod(0);
		// Setting logger for method start.
		LOGGER.info("ObjectValdator -> tokenSysValidator() - Started checking Employee Object.");
		//Call employeeNameValidator() of ObjectValidator class.
		this.employeeNameValidator(employee.getEmployeeName());
		//Call employeePinValidator() of ObjectValidator class.
		this.employeePinValidator(employee.getEmployeePin());
		//Call employeeTypeValidator() of ObjectValidator class.
		this.employeeTypeValidator(employee.getEmployeeType());
		//if block for check the errorDescription is null or not of Employee object.
		//if block for check the errorCod is null or not of Employee object.
		if(this.employee.getErrorDescription() == null && this.employee.getStatusCod() == 0) {
			// Setting logger for Object is in predefine format
			LOGGER.info("ObjectValdator -> tokenSysValidator() - Object is in predefine format.");
			//Setting the error Cod of the Employee. 
			employee.setStatusCod(200);
			//Setting the flag to true.
			this.flag = true;
			//Returning the Employee object.
			return employee;
		}
		//else block if if-block is false.
		else {
			// Setting logger for Object is not in predefine format.
			LOGGER.info("ObjectValdator -> tokenSysValidator() - Object is not is perdefine format");
			//Setting flag to false.
			this.flag = false;
			//Setting the error Description of the Employee. 
			employee.setErrorDescription(this.employee.getErrorDescription());
			//Setting the error Cod of the Employee. 
			employee.setStatusCod(this.employee.getStatusCod());
			//Setting erroDesc variable to empty String.
			this.errorDesc = "";
			//Returning object of Employee.
			return employee;
		}
	}
	
	/*
	 * This method is implemented for returning flag.
	 * This method is implemented only for test purpose.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- Boolean type true or false.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 31-12-23
	 */
	public boolean returnFlag() {
		return this.flag;
	}
	
	/*
	 * This method is implemented for converting the String variable to Operaters type.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param operation :- String variables for get operation from the user. 
	 * 
	 * @return :- value of Operaters.
	 * 
	 * @dateCreatd :- 03-12-23
	 * 
	 * @lastUpdate :- 23-01-24
	 */
	public Operaters toOperaters(String operation) {
		// Making value of operation to upper case.
		operation = operation.toUpperCase();
		// This is a try block which will execute as soon as toOperaters() will be
		// called.
		try {
			// Converting String to enum and returning the enum.
			return Operaters.valueOf(operation);
		}
		/*
		 * This is a catch block which will execute if try block throws any error.
		 * 
		 * @param e :- Object of Exception class.
		 */
		catch (Exception e) {
			// Throwing IllegalArgumentExcepion.
			LOGGER.info("ObjectValidator -> toOperaters() - Invalid Operation - " + operation);
			//returning null.
			return null;
		}
	}
	
	/*
	 * This method is implemented for checking weather the Employee name is in a predefine format or not.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param name :- variable of type String for getting name or employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 23-01-24
	 */
	private void employeeNameValidator(String name){
		//if block for checking the name is null or not.
		//if block for checking the name is empty or not.
		//if block for checking the name contain any numeric value or not.
		if(name == null || name.isEmpty() || !StringUtils.isAlpha(name)) {
			//if the name is empty, is null or contain number value then errorGenerator() will be call.
			errorGenerator("Employee Name");
		}
	}
	
	/*
	 * This method is implemented for checking weather the Employee type is in a predefine format or not.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param name :- variable of type String for getting type or employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 23-01-24
	 */
	private void employeeTypeValidator(String type){
		//if block for checking the type is null or not.
		//if block for checking the type is empty or not.
		//if block for checking the type contain any numeric value or not.
		if(type == null || type.isEmpty() || !StringUtils.isAlpha(type)) {
			//if the name is empty, is null or contain number value then errorGenerator() will be call.
			errorGenerator("Employee Type");
		}
	}
	
	/*
	 * This method is implemented for checking weather the Employee pin is in a predefine format or not.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param pin :- variable of type integer for getting pin or employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 31-12-23
	 */
	private void employeePinValidator(int pin) {
		//if block for checking the pin is or not 0.
		//if block for checking the pin length is 4 or not.
		if(pin == 0 || !(count(pin) == 4)) {
			//if the name is empty or contain number value then errorGenerator() will be call.
			errorGenerator("Employee Pin");
		}
	}

	/*
	 * This method is implemented for generating error message and error code.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param fieldName :- variable of type String for storing field name of employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 03-12-23
	 */
	private void errorGenerator(String fieldName) {
		//Creating error message with respect field.
		this.errorDesc =  this.errorDesc + "Invalid value/Format for " + fieldName + ", ";
		//Setting error message to errorDescription of employee.
		this.employee.setErrorDescription(errorDesc);
		//Setting error code to errorCod of employee.
		this.employee.setStatusCod(400);
	}
	
	/*
	 * This method is implemented for counting the length of employee pin.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param  :- variable of type String for storing field name of employee.
	 * 
	 * @dateCreatd :- 31-12-23
	 * 
	 * @lastUpdate :- 31-12-23
	 */
	private int count(int pin) {
		//Creating String variable stringPin for storing string format of integer pin. 
		String stringPin = Integer.toString(pin);
		//Returning length of string Pin.
		return stringPin.length();
	}
}