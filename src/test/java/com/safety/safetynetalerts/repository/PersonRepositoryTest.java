package com.safety.safetynetalerts.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.jacoco.maven.ReportAggregateMojo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepositoyImpl personRepository;
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void contextLoads() {
		assertNotNull(personRepository);
	}

	@Test
	public void getAllPersons() {
		List<Person> persons = personRepository.findAllPersons();
		List<Person> personsResult = dataSource.getPersons();
		assertNotNull(persons);
		assertNotNull(personsResult);
		assertEquals(persons, personsResult);
	}

	@Test
	public void deletePersonByFirstAndLastName() {
		boolean isRemoved = personRepository.deletePersonByFirstAndLastName("John", "Boyd");
		assertEquals(isRemoved, true);
	}

	@Test
	public void savePerson() {
		List<Person> persons = dataSource.getPersons();
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		Person personResult = personRepository.savePerson(person);
		boolean result = persons.contains(personResult);
		assertEquals(result, true);
	}

	@Test
	public void updatePerson() {
		List<Person> persons = dataSource.getPersons();
		Person person = new Person("John", "Boyd", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		Person personResult = personRepository.updatePersonByFirstAndLastName(person);
		boolean result = persons.contains(personResult);
		assertEquals(result, true);
	}

	@Test
	public void findPersonsByAddress() {
		PersonByAddressDto personByAddress = personRepository.findPersonsByAddress("1509 Culver St");
		assertNotNull(personByAddress);

	}

	@Test
	public void getEmailByCity() {
		List<String> emailList = personRepository.getEmailByCity("Culver");
		boolean result = emailList.contains("jaboyd@email.com");
		assertEquals(result, true);
	}
	
	@Test
	public void findChildrenByAddress() {
		ChildrenByAddressDto childrenByAddress= personRepository.findChildrenByAdress("1509 Culver St");
		assertNotNull(childrenByAddress);
	}
	
	@Test
	public void findPersonsByFirstAndLastName() {
		PersonByFirstEtLastNameDto personByFirstAndLastName= personRepository.findPersonsByFirstAndLastName("John", "Boyd");
		assertNotNull(personByFirstAndLastName);
	}
}
