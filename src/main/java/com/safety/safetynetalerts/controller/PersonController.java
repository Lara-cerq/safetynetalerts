package com.safety.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.service.PersonService;

@CrossOrigin
@RestController
@RequestMapping(path="/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/allPersons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
)
	public List<Person> findPersons() {
		return personService.getAllPersons();
	}
	
	// http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
	@GetMapping("/personInfo")
	public PersonByFirstEtLastNameDto findPerson(@RequestParam String firstName, String lastName) {
		return personService.getPersonByFirstLastName(firstName, lastName);
	}

//	//http://localhost:8080/fire?address=<address>
	@GetMapping("/fire")
	public PersonByAddressDto findPersonByAdress(@RequestParam String address) {
		return personService.getPersonsByAdresse(address);
	}
	
	@RequestMapping(value = "/addPerson", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
)
	public Person addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}
	
	// http://localhost:8080/communityEmail?city=<city>
	@GetMapping("/communityEmail") 
	@ResponseBody
	public List<String> getEmailByCity(@RequestParam String city) {
		return personService.getEmailByCity(city);
	}
	
//	http://localhost:8080/childAlert?address=<address>
	@GetMapping("/childAlert") 
	public ChildrenByAddressDto findChildrenByAddress(@RequestParam String address) {
		return personService.findChildrenByAddress(address);
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
