package com.perseverance.india.TokenSystem.repository.Mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.perseverance.india.TokenSystem.model.Employee;

//@Repository annotation is used to declare an interface as a repository with specified name. 
@Repository("MongoRepo")
public interface EntityRepoMongo extends MongoRepository<Employee, String> {

	/*
	 * This is a custom method which is declared for finding document in the basics
	 * of _id field of document
	 * 
	 * @author :- Hrushikesh
	 * 
	 * @param id :- variable use to get id of the employee.
	 * 
	 * @return :- information of specific employee.
	 * 
	 * @dateCreated :- 22-01-23
	 * 
	 * @lastUpdate :- 22-01-23
	 */
	// @Query annotation is used to write custom queries as we needed.
	@Query("{ '_id' : ?0 }")
	public Employee findBy_id(String id);
}