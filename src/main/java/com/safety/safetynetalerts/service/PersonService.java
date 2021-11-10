package com.safety.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.repository.PersonRepository;

import lombok.Data;

@Data
@Service("personService")
public class PersonService {

	@Autowired(required=true)
	private PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getPerson(String firstName, String lastName) {
		return personRepository.findByFirstAndLastName(firstName, lastName);
	}

	public Person addPerson(Person person) {
		person.setFirstName(person.getFirstName());
		person.setLastName(person.getLastName());
		person.setAdress(person.getAddress());
		person.setCity(person.getCity());
		person.setZip(person.getZip());
		person.setEmail(person.getEmail());
		person.setPhone(person.getPhone());
		person.setId(person.getId());
		Person addedPerson = personRepository.savePerson(person);
		return addedPerson;
	}

	public Person updatePerson(String firstName, String lastName) {
		personRepository.findByFirstAndLastName(firstName, lastName);
		Person person = new Person();
		person.setLastName(person.getLastName());
		person.setAdress(person.getAddress());
		person.setCity(person.getCity());
		person.setZip(person.getZip());
		person.setEmail(person.getEmail());
		person.setPhone(person.getPhone());

		Person addedPerson = personRepository.savePerson(person);
		return addedPerson;
	}

	public void deletePerson(String firstName, String lastName) {
		personRepository.deleteByFirstAndLastName(firstName, lastName);
	}
}
