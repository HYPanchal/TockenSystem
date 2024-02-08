package com.perseverance.india.TokenSystem.services;

import java.util.List;
import java.util.Objects;

import org.aspectj.lang.annotation.Before;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perseverance.india.TokenSystem.model.Employee;
import com.perseverance.india.TokenSystem.model.Operaters;

/*
 * Service class responsible for handling operations related to employee management.
 * @author :- Hrushikesh
 * @dateCreated :- 03-12-23
 */
//@Service annotation in Spring is used to indicate that a class is a service class
@Service
public class HeadService {
	// Injected dependency of CrudServices class by using @Autowired annotation
	// as crudServices.
	@Autowired
	private CrudServices crudServices;
	// Injected dependency of ObjectValidator class by using @Autowired annotation
	// as objectValidator.
	@Autowired
	private ObjectValidator objectValidator;
	// Initialized static final variable of Logger as LOGGER. Logger is use to
	// record the runtime behavior of the application.
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HeadService.class);
	private boolean operationFlage;

	/*
	 * This method is implemented for performing operations related employee
	 * management.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Request Body of type Employee expecting form the front
	 * end.
	 * 
	 * @param operation :- Variable of type enum expecting from front end for
	 * deciding which operation should be perform. e.g:- saving, editing and
	 * deleting employee.
	 * 
	 * @return :- Object of type Employee.
	 * 
	 * @dateCreatd :- 03-12-23
	 * 
	 * @lastUpdate :- 30-01-24
	 */
	public Employee headService(Employee employee, String operation) {
		// Setting logger for method start.
		LOGGER.info("HeadService -> headService() - method started.");
		// Calling toOperaters() of ObjectValidator class and storing in into operaters
		// variables
		Operaters operaters = objectValidator.toOperaters(operation);
		// Checking if operaters variable is null or not.
		if (operaters != null) {
			// Initializing temporary variable as tempEmp of type Employee.
			Employee tempEmp = null;
			// Checking that the employee variable is valid or not.
			tempEmp = objectValidator.tokenSysValidator(employee);
			// empExistorNot is a temporary variable to store the return of getEmp method.
			Employee empExistorNot = crudServices.getEmpById(employee.getEmployeeId());
			// Checking if empExistorNot variable is null or not.
			// if it is not null then else block will executed.
			if (Objects.isNull(empExistorNot)) {
				// Checking if Flag is true or false.
				// if false then else block will executed.
				if (objectValidator.returnFlag()) {
					/*
					 * if employee is valid and exist.
					 */
					switch (operaters) {
					// case for saving the employee.
					case SAVE_EMP:
						// calling saveEmp() of CrudEmployee class and passing employee as a parameter.
						tempEmp = crudServices.saveEmp(employee);
						// breaking the loop after employee has been saved.
						break;
					// default case if the operator is invalid.
					default:
						/*
						 * if employee is valid and exist but invalid operation.
						 */
						// Setting error description.
						tempEmp.setErrorDescription("Employee Dose Not Exist");
						// Setting status code.
						tempEmp.setStatusCod(404);
					}
					// returning Employee object.
					return tempEmp;
				} else {
					if (operaters == Operaters.DELETE_EMP || operaters == Operaters.EMP) {
						// Setting error description.
						employee.setErrorDescription("Employee Dose Not Exist");
						// Setting status code.
						employee.setStatusCod(404);
						// returning Employee object.
						return employee;
					} else {
						// returning Employee object.
						return tempEmp;
					}
				}
			} else {
				// Checking if Flag is true or false.
				// Checking if operaters is EMP or not
				// if false then else block will executed.
				if (objectValidator.returnFlag() || operaters == Operaters.EMP || operaters == Operaters.DELETE_EMP) {
					// if empExistorNot is not null then switch will check the cases.
					switch (operaters) {
					// case for editing employee.
					case EDIT_EMP:
						// calling editEmp() of CrudServices class and passing employee as a parameter.
						employee = crudServices.editEmp(employee);
						// breaking the loop after employee has been edited.
						break;
					// case for deleting employee.
					case DELETE_EMP:
						// calling deleteEmp() of CrudServices class and passing employee as a
						// parameter.
						employee = crudServices.deleteEmp(employee);
						// breaking the loop after employee has been deleted.
						break;
					// case for getting employee
					case EMP:
						// calling getEmpById() of CrudServices class and passing employeeId as a
						// parameter.
						employee = crudServices.getEmpById(employee.getEmployeeId());
						// breaking the loop after employee has been received.
						break;
					// default case if the operator is invalid.
					default:
						// Setting error Description Invalid operation.
						employee.setErrorDescription("Employee Already Exist");
						// Setting status cod 404 Not Found.
						employee.setStatusCod(404);
					}
					// returning Employee object.
					return employee;
				} else {
					// returning Employee object.
					return tempEmp;
				}
			}
		} else {
			// Setting error Description Invalid operation.
			employee.setErrorDescription("No Operaton Found");
			// Setting status cod 400 bad request.
			employee.setStatusCod(400);
			// returning Employee object.
			return employee;
		}
	}

	/*
	 *This method is implemented for performing operations related employee
	 * management.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param employee :- Request Body of type Employee expecting form the front
	 * end.
	 * 
	 * @param operation :- Variable of type enum expecting from front end for
	 * deciding which operation should be perform. e.g:- saving, editing and
	 * deleting employee.
	 * 
	 * @return :- Object of type Employee.
	 * 
	 * @dateCreatd :- 03-02-24
	 * 
	 * @lastUpdate :- 04-02-24
	 */
	public Employee headservice(String operation, Employee employee) {
		//Created temporary variable of type Employee for storing and returning purpose.
		Employee tempEmployee = this.validater(operation, employee);
		//Checking the operation is valid or not.
		if (this.operationFlage) {
			//Calling toOperaters() of ObjectValidator class and storing it into operaters variable.
			Operaters operaters = objectValidator.toOperaters(operation);
			//Checking the employee object is in predefined format or not.
			if (objectValidator.returnFlag()) {
				//Checking the employee object is exist in DB or not.
				if (Objects.isNull(crudServices.getEmpById(tempEmployee.getEmployeeId()))) {
					//Checking the operation is for save employee or not
					if (operaters == Operaters.SAVE_EMP) {
						//Calling saveEmp() of CrudServices class and returning the employee.
						return crudServices.saveEmp(tempEmployee);
					//if the operation is not for save employee.
					} else {
						//Setting error description.
						tempEmployee.setErrorDescription("Employee Dose Not Exist");
						//Setting status code to 404
						tempEmployee.setStatusCod(404);
						//Returning employee.
						return tempEmployee;
					}
				}
				//if employee object exist in DB.
				else {
					//Checking the operation is for edit employee or not
					if(operaters == Operaters.EDIT_EMP) {
						//Calling editEmp() of CrudServices class and returning the employee.
						return crudServices.editEmp(tempEmployee);
					}
				}
			//if the employee object is not in predefined format.
			} else {
				//
				if (!Objects.isNull(crudServices.getEmpById(tempEmployee.getEmployeeId()))) {
					switch (operaters) {
					case DELETE_EMP:
						tempEmployee = crudServices.deleteEmp(tempEmployee);
						break;
					case EMP:
						tempEmployee = crudServices.getEmpById(tempEmployee.getEmployeeId());
						break;
					}
					return tempEmployee;
				} else {
//					tempEmployee.setErrorDescription("Employee Dose Not Exist");
//					tempEmployee.setStatusCod(404);
					return tempEmployee;
				}
			}
		} else {
			return tempEmployee;
		}
		tempEmployee.setErrorDescription("Employee Already Exist");
		tempEmployee.setStatusCod(400);
		return tempEmployee;
	}

	private Employee validater(String operation, Employee employee) {
		if (!Objects.isNull(objectValidator.toOperaters(operation))) {
			this.operationFlage = true;
			Employee tempEmployee = objectValidator.tokenSysValidator(employee);
			return tempEmployee;
		} else {
			this.operationFlage = false;
			employee.setErrorDescription("No Operaton Found");
			employee.setStatusCod(400);
			return employee;
		}
	}

	/*
	 * This method is implemented for retrieving all the employee's stored in the
	 * DB.
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @return :- List of objects of type employee.
	 * 
	 * @dateCreated :- 03-12-23
	 * 
	 * @lastUpdate :- 16-01-24
	 */
	public List<Employee> empList() {
		// returning List of Employee object.
		return crudServices.getAllEmp();
	}
}