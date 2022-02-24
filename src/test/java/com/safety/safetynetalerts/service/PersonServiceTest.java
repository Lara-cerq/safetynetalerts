package com.safety.safetynetalerts.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.model.PersonDto;
import com.safety.safetynetalerts.model.PersonNameDto;
import com.safety.safetynetalerts.model.PersonNameEmailDto;
import com.safety.safetynetalerts.model.PersonNamePhoneStationDto;
import com.safety.safetynetalerts.repository.PersonRepositoyImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepositoyImpl personRepository;

	@Test
	public void getAllPersons() {
		List<Person> persons = new ArrayList<>();
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		persons.add(person);
		Mockito.when(personRepository.findAllPersons()).thenReturn(persons);
		List<Person> personsResultat = personService.getAllPersons();
		assertEquals(personsResultat, persons);
	}

	@Test
	public void addPerson() {
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		Mockito.when(personRepository.savePerson(person)).thenReturn(person);
		Person personResultat = personService.addPerson(person);
		assertEquals(personResultat, person);
	}

	@Test
	public void updatePerson() {
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		Mockito.when(personRepository.updatePersonByFirstAndLastName(person)).thenReturn(person);
		Person personResultat = personService.updatePerson(person);
		assertEquals(personResultat, person);
	}

	@Test
	public void deletePerson() {
		Mockito.when(personRepository.deletePersonByFirstAndLastName("Lara", "Cerqueira")).thenReturn(true);
		boolean personResultat = personService.deletePerson("Lara", "Cerqueira");
		assertEquals(true, personResultat);
	}

	@Test
	public void getPersonsByAdresse() {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		Person person = new Person("Lara", "Cerqueira", "15 Rue Verdi", "Nice", 06000L, "0123225", "lksj@hot.com");
		FireStation station = new FireStation("Nice", 1);
		List<PersonNamePhoneStationDto> personDtolist = new ArrayList<>();
		PersonNamePhoneStationDto personDto = new PersonNamePhoneStationDto(person.getFirstName(),
				person.getFirstName(), person.getAddress(), medications, allergies, 18L);
		personDtolist.add(personDto);
		PersonByAddressDto personByAddressDto = new PersonByAddressDto(personDtolist, station.getStation());

		Mockito.when(personRepository.findPersonsByAddress("Nice")).thenReturn(personByAddressDto);
		PersonByAddressDto personByAddressDtoResultat = personService.getPersonsByAdresse("Nice");
		assertEquals(personByAddressDto, personByAddressDtoResultat);
	}

	@Test
	public void getEmailByCity() {
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		String email = person.getEmail();
		List<String> emailList = new ArrayList<>();
		emailList.add(email);
		Mockito.when(personRepository.getEmailByCity("Nice")).thenReturn(emailList);
		List<String> emailListResultat = personService.getEmailByCity("Nice");
		assertEquals(emailList, emailListResultat);

	}

	@Test
	public void findChildrenByAddress() {
		PersonNameDto personNameDto = new PersonNameDto("Lara", "Cerqueira", 18L);
		List<PersonNameDto> personNameList = new ArrayList<>();
		personNameList.add(personNameDto);
		PersonDto personDto = new PersonDto("Junior", "Cerqueira");
		List<PersonDto> personList = new ArrayList<>();
		personList.add(personDto);
		ChildrenByAddressDto childrenByAddress = new ChildrenByAddressDto(personNameList, personList);

		Mockito.when(personRepository.findChildrenByAdress("Nice")).thenReturn(childrenByAddress);

		ChildrenByAddressDto childrenByAddressResultat = personService.findChildrenByAddress("Nice");
		assertEquals(childrenByAddress, childrenByAddressResultat);
	}

	@Test
	public void findPersonByFirstAndLastName() {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		PersonNameEmailDto personNameEmail = new PersonNameEmailDto("Lara", "Cerqueira", "lksj@hot.com", "15 Rue Verdi",
				medications, allergies, 18L);
		List<PersonNameEmailDto> personNameEmailList = new ArrayList<>();
		personNameEmailList.add(personNameEmail);
		PersonByFirstEtLastNameDto personByFirstEtLastName = new PersonByFirstEtLastNameDto(personNameEmailList);
		Mockito.when(personRepository.findPersonsByFirstAndLastName("Lara", "Cerqueira"))
				.thenReturn(personByFirstEtLastName);
		PersonByFirstEtLastNameDto personByFirstEtLastNameResult = personRepository
				.findPersonsByFirstAndLastName("Lara", "Cerqueira");
		assertEquals(personByFirstEtLastName, personByFirstEtLastNameResult);
	}
}
