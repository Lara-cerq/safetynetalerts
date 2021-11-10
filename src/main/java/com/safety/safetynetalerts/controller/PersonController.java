package com.safety.safetynetalerts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.repository.PersonRepository;
import com.safety.safetynetalerts.service.PersonService;

@CrossOrigin
@RestController
@RequestMapping(path="/persons")
public class PersonController {

	@Autowired
//	@Qualifier("personService")
	private PersonService personService;

	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
)
	public Person addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}
	
	@RequestMapping(value = "/person{firstName}/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
)
	public List<Person> findPerson(@PathVariable String firstName, String lastName) {
		return personService.getPerson(firstName, lastName);
	}

//	@GetMapping("/person/{firstName}/{lastName}")
//	@RequestMapping(value = "/person/{firstName}/{lastName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Person updatePerson(@PathVariable("firstName, lastName") final String firstName, final String lastName,
//			@RequestBody Person person) {
//		Optional<Person> p = personService.getPerson(firstName, lastName);
//		if (p.isPresent()) {
//			Person currentPerson = p.get();
//
//			String adress = person.getAdress();
//			if (adress != null) {
//				currentPerson.setAdress(adress);
//			}
//			String city = person.getCity();
//			if (adress != null) {
//				currentPerson.setCity(city);
//			}
//
//			Long zip = person.getZip();
//			if (zip != null) {
//				currentPerson.setZip(zip);
//			}
//			String email = person.getEmail();
//			if (email != null) {
//				currentPerson.setEmail(email);
//			}
//			String phoneNumber = person.getPhoneNumber();
//			if (phoneNumber != null) {
//				currentPerson.setPhoneNumber(phoneNumber);
//			}
//			personService.addPerson(currentPerson);
//			return currentPerson;
//		} else {
//			return null;
//		}
		
//		@GetMapping("/person/{firstName}/{lastName}")
//		public void deletePerson(@PathVariable("firstName, lastName") final String firstName, final String lastName) {
//			personService.deletePerson(firstName, lastName);
//		}
//	}
	
	
}
