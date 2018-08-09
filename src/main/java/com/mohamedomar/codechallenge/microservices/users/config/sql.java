package com.mohamedomar.codechallenge.microservices.users.config;

public class sql {
	/*
	Enter your query here.
	Please append a semicolon ";" at the end of the query
	*/
	/*Basically i have to do a sub query that fetches the existing data from the 3 seed table with an in() function and then do left joins with schedule table. i will get back to this later on when i finish the coding part*/

	/**select p.name, d.name, c.name from department d, professor p, course c , schedule s where
	s.professor_id =p.id
	and s.course_id = c.id
	and p.department_id = d.id
	and c.department_id = d.id*/
}
