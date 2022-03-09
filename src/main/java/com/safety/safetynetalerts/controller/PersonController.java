package com.safety.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class PersonController implements HealthIndicator {

	@Autowired
	private PersonService personService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@RequestMapping(value = "/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findPersons() {
		logger.info("All persons");
		return personService.getAllPersons();
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		Person personSaved = personService.addPerson(person);
		logger.info("PERSON CREATED");
		return new ResponseEntity<>(personSaved, HttpStatus.CREATED);
	}

	@PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Person> updatePerson(@Validated @RequestBody Person newPerson)
			throws IOException {
		Person personUpdated = personService.updatePerson(newPerson);
		logger.info("PERSON UPDATED");
		return new ResponseEntity<>(personUpdated, HttpStatus.OK);

	}

	@DeleteMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deletePerson(@RequestParam String firstName, @RequestParam String lastName)
			throws IOException {
		boolean isRemoved = personService.deletePerson(firstName, lastName);
		if (!isRemoved) {
			logger.info("PERSON NOT DELETED");
		}
		logger.info("PERSON DELETED");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/personInfo")
	public ResponseEntity<PersonByFirstEtLastNameDto> findPerson(@RequestParam String firstName, String lastName) {
		logger.info("Finding persons by first name=" + firstName + " and lastName=" + lastName);
		PersonByFirstEtLastNameDto personFinded= personService.getPersonByFirstLastName(firstName, lastName);
		return new ResponseEntity<>(personFinded, HttpStatus.OK);
	}

	@GetMapping("/fire")
	public ResponseEntity<PersonByAddressDto> findPersonByAddress(@RequestParam String address) {
		PersonByAddressDto personByAddress= personService.getPersonsByAdresse(address);
		return new ResponseEntity<>(personByAddress, HttpStatus.OK);
	}

	@GetMapping("/communityEmail")
	@ResponseBody
	public ResponseEntity<List<String>> getEmailByCity(@RequestParam String city) {
		List<String> emailList= personService.getEmailByCity(city);
		return new ResponseEntity<>(emailList, HttpStatus.OK);
	}

	@GetMapping("/childAlert")
	public ResponseEntity<ChildrenByAddressDto> findChildrenByAddress(@RequestParam String address) {
		ChildrenByAddressDto childrenByAddress= personService.findChildrenByAddress(address);
		return new ResponseEntity<>(childrenByAddress, HttpStatus.OK);
	}

	@Override
	public Health health() {
		List<Person> persons = personService.getAllPersons();
		if (persons.isEmpty()) {
			return Health.down().build();
		}
		return Health.up().build();
	}
}
