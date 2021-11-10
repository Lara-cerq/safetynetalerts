package com.safety.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.model.Person;

import lombok.Data;

@Data
@Repository("personRepository")
public interface PersonRepository {

	public List<Person> persons = new ArrayList<Person>();

	public List<Person> findByFirstAndLastName(String firstName, String lastName);
//	{
//		for (Person person : persons) {
//			if (person.getFirstName() == firstName && person.getLastName() == lastName) {
//				return persons;
//			}
//		}
//		return null;
//	}

	public Person deleteByFirstAndLastName(String firstName, String lastName);

	public Person updateByFirstAndLastName(String firstName, String lastName);

	public Person savePerson(Person person);
}
