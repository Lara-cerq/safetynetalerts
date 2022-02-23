package com.safety.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.repository.PersonRepositoyImpl;

@Service("personService")
public class PersonService {

	@Autowired(required = true)
	private PersonRepositoyImpl personRepository;

	public PersonService(PersonRepositoyImpl personRepository) {
		super();
		this.personRepository = personRepository;
	}

	public void setPersonRepository(PersonRepositoyImpl personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAllPersons();
	}

	public PersonByFirstEtLastNameDto getPersonByFirstLastName(String firstName, String lastName) {
		return personRepository.findPersonsByFirstAndLastName(firstName, lastName);
	}

	public PersonByAddressDto getPersonsByAdresse(String address) {
		return personRepository.findPersonsByAddress(address);
	}

	public Person addPerson(Person person) {
		return personRepository.savePerson(person);
	}

    public boolean deletePerson(String firstName, String lastName) {
    	return personRepository.deletePersonByFirstAndLastName(firstName, lastName);
    }
	
	public Person updatePerson(Person person){
		return personRepository.updatePersonByFirstAndLastName(person);
	}

	public List<String> getEmailByCity(String city) {
		return personRepository.getEmailByCity(city);
	}
	
	public ChildrenByAddressDto findChildrenByAddress(String address) {
		return personRepository.findChildrenByAdress(address);
	}
}
