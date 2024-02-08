package com.perseverance.india.TokenSystem.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perseverance.india.TokenSystem.services.CrudServices;
import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.repository.Mongo.EntityRepoMongo;

/*
 * This is Service class which provide all types of crud operations.
 * This class typically contain business logic, perform service-layer operations, 
 * and act as a bridge between controllers and data access objects.
 * 
 * @author :- Hrushikesh
 * 
 * @dateCreated :- 03-12-23
 */
//@Service annotation in Spring is used to indicate that a class is a service class
@Service
public class CrudServices {
	// Injected dependency of EntityRepoMongo interface by using @Autowired annotation
	// as employeeRepo
	@Autowired
	private EntityRepoMongo entityRepoMongo;
	// Initialized static final variable of Logger as LOGGER. Logger is use to
	// record the runtime behavior of the application.
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CrudServices.class);

	/*
	 * This method is implemented to save the employee in the data base.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Variable use to get object of type Employee as a
	 * parameter.
	 * 
	 * @return :- Object of type Employee
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 23-01-24
	 */
	protected Employee saveEmp(Employee employee) {
		// This is try block which will execute as soon as saveEmp() is called.
		try {
			// Calling save() of EmployeeRepo interface and saving the employee.
			// If this method gives an error then catch block will be execute.
			entityRepoMongo.save(employee);
			// Setting logger for saving employee successfully.
			LOGGER.info("CrudService -> saveEmp() - Employee has been saved");
			// Returning the saved employee.
			return employee;
			/*
			 * This is a catch block which will execute if try block throws any error.
			 * 
			 * @param e :- Object of Exception class.
			 * 
			 * @return :- null object of employee.
			 */
		} catch (Exception e) {
			// Setting logger for unsuccessful attempt of saving employee in to the data
			// base.
			LOGGER.info("CrudService -> saveEmp() - Fail to save the Employee");
			// Printing stack trace of error on the console.
			e.printStackTrace();
			// Returning null object of employee.
			return null;
		}
	}

	/*
	 * This method is implemented to edit the employee which already exist in data
	 * base.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Variable use to get object of type Employee as a
	 * parameter.
	 * 
	 * @return :- Object of type Employee
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 23-01-24
	 */
	protected Employee editEmp(Employee employee) {
		// tempEmp is an temporary object of Employee use to return after completion of method.
		Employee tempEmp = null;
		// This is try block which will execute as soon as editEmp() is called.
		try {
			// Calling findById() of EmployeeRepo interface and assigning it to tempEmp
			// variable.
			// If this method gives an error then catch block will be execute.
			tempEmp = entityRepoMongo.findBy_id(employee.getEmployeeId());
			// Checking that if the name of employee is null or not.
			if (employee.getEmployeeName() != null) {
				// if the name is not null.
				// Setting the name of employee into temporary variable tempEmp.
				tempEmp.setEmployeeName(employee.getEmployeeName());
				// Checking that if the pin of employee is null of not.
			}if (employee.getEmployeePin() != 0) {
				// if the pin is not null.
				// Setting the pin of employee into temporary variable tempEmp.
				tempEmp.setEmployeePin(employee.getEmployeePin());
				// Checking that if the type of employee is null of not.
			}if(employee.getEmployeeType() != null) {
				// if the type is not null.
				// Setting the type of employee into temporary variable tempEmp.
				tempEmp.setEmployeeType(employee.getEmployeeType());
			}
			//Setting error description null.
			tempEmp.setErrorDescription(null);
			//Setting status cod 200;
			tempEmp.setStatusCod(200);
			// Calling save() of EmployeeRepo interface and saving the updated employee.
			// If this method gives an error then catch block will be execute.
			entityRepoMongo.save(tempEmp);
			// Setting logger for successfully updating the employee.
			LOGGER.info("CrudService -> editEmp() - Employee has beed updated");
			// Returning the updated employee.
			return tempEmp;
			/*
			 * This is a catch block which will execute if try block throws any error.
			 * 
			 * @param e :- Object of Exception class.
			 * 
			 * @return :- null object of employee.
			 */
		} catch (Exception e) {
			// Setting logger for unsuccessful attempt of updating employee in to the data
			// base.
			LOGGER.info("CrudService ", "saveEmp() - Fail to update the Employee");
			// Printing stack trace of error on the console.
			e.printStackTrace();
			// Returning null object of employee.
			return null;
		}
	}

	/*
	 * This method is implemented to delete the employee which already exist in data
	 * base.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Variable use to get object of type Employee as a
	 * parameter.
	 * 
	 * @return :- Object of type Employee
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdare :- 23-01-24
	 */
	protected Employee deleteEmp(Employee employee) {
		// This is try block which will execute as soon as deleteEmp() is called.
		try {
			// Calling delete() of EntityRepoMongo interface and deleting the employee.
			entityRepoMongo.delete(employee);
			// Setting logger for successfully deleting the employee.
			LOGGER.info("CrudService -> deleteEmp() - Employee has been deleted");
			//Setting error description null.
			employee.setErrorDescription(null);
			//Setting status cod 200 OK.
			employee.setStatusCod(200);
			// Returning null object of employee.
			return employee;
			/*
			 * This is a catch block which will execute if try block throws any error.
			 * 
			 * @param e :- Object of Exception class.
			 * 
			 * @return :- null object of employee.
			 */
		} catch (Exception e) {
			// Setting logger for unsuccessful attempt of deleting employee from the data
			// base.
			LOGGER.info("CrudService -> saveEmp() - Fail to delete the Employee");
			// Printing stack trace of error on the console.
			e.printStackTrace();
			//Setting error description Employee not deleted.
			employee.setErrorDescription("Employee not deleted");
			//Setting status cod 202 Accepted.
			employee.setStatusCod(202);
			// Returning object of employee which have not deleted.
			return employee;
		}
	}
	
	/*
	 * This method is implemented for getting a specific employee.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param id :- variable use to get id of the employee.
	 * 
	 * @return :- information of specific employee.
	 * 
	 * @dateCreated :- 22-01-23
	 * 
	 * @lastUpdate :- 23-01-24
	 */
	public Employee getEmpById(String id) {
		// This is a try block which will execute as soon as getEmp() will be called.
		try {
			// Setting logger for successfully returning the employee.
			LOGGER.info("CrudService -> getEmp() - Employee returned");
			// Retrieving information of employee from the DB if exist
			return entityRepoMongo.findBy_id(id);
			/*
			 * This is a catch block which will execute if try block throws any error.
			 * 
			 * @param e :- Object of Exception class.
			 */
		} catch (Exception e) {
			// Setting logger if any error occur.
			LOGGER.info("CrudService -> getEmp() - Employee Not Found.");
			// Returning the null.
			return null;
		}
	}
	
	/*
	 * This method is implemented for retrieving all the employee's stored in the
	 * DB.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param id :- variable use to get id of the employee.
	 * 
	 * @return :- information of specific employee.
	 * 
	 * @dateCreated :- 22-01-23
	 * 
	 * @lastUpdate :- 22-01-24
	 */
	public List<Employee> getAllEmp(){
		// This is a try block which will execute as soon as getEmp() will be called.
		try {
			// Setting logger for successfully returning the list of employee.
			LOGGER.info("CrudService -> empList() - Employee List returned");
			// List of employee has been returned.
			return entityRepoMongo.findAll();
			/*
			 * This is a catch block which will execute if try block throws any error.
			 * 
			 * @param e :- Object of Exception class.
			 */
		} catch (Exception e) {
			// Setting logger for Error.
			e.printStackTrace();
			// Setting logger if any error occur.
			LOGGER.info("CrudService -> empList() - Error");
			//returning null.
			return null;
		}
	}
}