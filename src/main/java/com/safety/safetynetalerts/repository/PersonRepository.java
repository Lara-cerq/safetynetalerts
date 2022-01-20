package com.safety.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;

public interface PersonRepository {

	public List<Person> persons = new ArrayList<Person>();

	public List<Person> findAllPersons();
	
	public PersonByAddressDto findPersonsByAddress(String address);

	public Person deletePersonByFirstAndLastName(String firstName, String lastName);

	public Person updatePersonByFirstAndLastName(String firstName, String lastName);

	public Person savePerson(Person person);
	
	public List<String> getEmailByCity(String email);
	
	public PersonByFirstEtLastNameDto findPersonsByFirstAndLastName(String firstName, String lastName);
	
	public ChildrenByAddressDto findChildrenByAdress(String address);
}
