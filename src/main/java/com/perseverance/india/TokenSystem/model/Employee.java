package com.perseverance.india.TokenSystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/*
 * Represents a Employee entity in the system.
 * 
 * @author :- Hrushikesh
 * 
 * @dateCreated :- 03-12-23
 */
//This class is annotated with @Entity to indicate that it is a JPA entity.
@Entity
//@Table annotation  is used to specify the details of the database table to which an entity is mapped.
//In particular, the name attribute is used to specify the name of the table.
@Table(name = "Employee")
//@Document annotation is used in the context of Spring Data MongoDB. 
@Document(collection = "Employee")
public class Employee {
	/*
	 * The unique identifier for the employee. This field is annotated with @Id to
	 * mark it as the primary key. 
	 */
	@Id
	String employeeId;
	// employee name for the Employee
	String employeeName;
	// employee pin for the Employee
	int employeePin;
	// employee type for the Employee
	String employeeType;
	// Error description if any
	String errorDescription;
	//Error code if any
	int statusCode;

	/*
	 * This is a Parameterized constructor for the Employee class use to initialize
	 * a object of the Employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empId :- Variable of type integer use to initialize the id of the
	 * employee
	 * 
	 * @param empName :- Variable of String type use to initialize the name of the
	 * employee
	 * 
	 * @param empPin :- Variable of type integer use to initialize the pin of the
	 * employee
	 * 
	 * @param emptype :- Variable of type String use to initialize the type of the
	 * employee e.g:- waiter
	 * 
	 * @return :- constructor don't return any thing
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 31-12-23
	 */
	public Employee(String empName, int empPin, String empType) {
		super();
		this.employeeName = empName;
		this.employeePin = empPin;
		this.employeeType = empType;
	}

	/*
	 * Default constructor for the Employee class use to create object of the
	 * Employee class
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 26-12-23
	 */
	public Employee() {
		super();
	}

	/*
	 * This is a getter method use to get the id of a specific employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- employee id type integer
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 22-02-23
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/*
	 * This is a setter method use to set value in employeeId variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empId :- Variable of type integer use to initialize the id of employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 22-01-23
	 */
	public void setEmployeeId(String empId) {
		employeeId = empId;
	}

	/*
	 * This is a getter method use to get the name of a specific employee.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- employee name type String.
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/*
	 * This is a setter method use to set value in employeeName variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empName :- Variable of type String use to initialize the name of
	 * employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public void setEmployeeName(String empName) {
		employeeName = empName;
	}

	/*
	 * This is a getter method use to get the pin of a specific employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- employee pin type integer
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public int getEmployeePin() {
		return employeePin;
	}

	/*
	 * This is a setter method use to set value in employeePin variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empPin :- Variable of type integer use to initialize the pin of
	 * employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public void setEmployeePin(int empPin) {
		employeePin = empPin;
	}

	/*
	 * This is a getter method use to get the type of a specific employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- employeeType type String
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	/*
	 * This is a setter method use to set value in employeeType variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empType :- Variable of type String use to initialize the type of
	 * employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public void setEmployeeType(String empType) {
		employeeType = empType;
	}

	/*
	 * This is a getter method use to get the Error Description of a specific employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- employeeType type String
	 * 
	 * @dateCreated :- 28-12-23
	 * 
	 * @lastUpdare :- 26-12-23
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/*
	 * This is a setter method use to set value in Error Description variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empType :- Variable of type String use to initialize the type of
	 * employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 28-12-23
	 * 
	 * @lastUpdare :- 28-12-23
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/*
	 * This is a getter method use to get the Error code of a specific employee
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- code of error integer
	 * 
	 * @dateCreated :- 31-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public int getStatusCod() {
		return statusCode;
	}

	/*
	 * This is a setter method use to set value in Error code variable
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param empType :- Variable of type String use to initialize the type of
	 * employee
	 * 
	 * @return :- This method is void
	 * 
	 * @dateCreated :- 31-12-23
	 * 
	 * @lastUpdare :- 03-12-23
	 */
	public void setStatusCod(int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * This method is annotated with @Override and used to define the format of
	 * printing the object of employee on console
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- Returns format of object to be display in String.
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 31-12-23
	 */
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeePin=" + employeePin
				+ ", employeeType=" + employeeType + ", errorDescription=" + errorDescription + ", statusCode="
				+ statusCode + "]";
	}
}