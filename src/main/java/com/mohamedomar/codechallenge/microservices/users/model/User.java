package com.mohamedomar.codechallenge.microservices.users.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent user entity with JPA markup. users are stored in an H2
 * relational database.
 * 
 * @author Mohamed Omar
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private static Long nextId = 0L;
	
	@Id
	private Long id;
	
	@Column
	private String email;

	@Column
	private String name;

	@Column
	private String description;


	/**
	 * Default constructor for JPA only.
	 */
	public User() {
		this.id = getNextId();
	}
	
	/**
	 * since we are using an in memory dayabase,
	 * naive implementation solution to generating unique
	 * ids. in real application, better use
	 * <tt>@GeneratedValue</tt> annotation and a database sequence to generate ids.
	 * 
	 * @return The next available id.
	 */
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//TODO classic mistake, please over ride hashCode()!!!
	@Override
	public String toString() {
		return name + " [" + email + "]:" + description;
	}

}
