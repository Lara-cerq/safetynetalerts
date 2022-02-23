package com.safety.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynetalerts.controller.PersonController;
import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.model.PersonDto;
import com.safety.safetynetalerts.model.PersonNameDto;
import com.safety.safetynetalerts.model.PersonNameEmailDto;
import com.safety.safetynetalerts.model.PersonNamePhoneStationDto;
import com.safety.safetynetalerts.repository.PersonRepositoyImpl;
import com.safety.safetynetalerts.service.PersonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@MockBean
	private PersonService personService;

	@Autowired
	public MockMvc mockMvc;

	@Test
	public void testGetPersons() throws Exception {
		List<Person> persons = new ArrayList<>();
		Person person = new Person("Lara", "Cerqueira", "15 Rue verdi", "Nice", 06000L, "0123456", "lara@hotmail.com");
		persons.add(person);
		Mockito.when(personService.getAllPersons()).thenReturn(persons);
		mockMvc.perform(get("/persons")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Lara")));
	}

	@Test
	public void testSavePersons() throws Exception {
		Person person = new Person("Lara", "Cerqueira", "15 Rue verdi", "Nice", 06000L, "0123456", "lara@hotmail.com");
		Mockito.when(personService.addPerson(Mockito.any(Person.class))).thenReturn(person);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/person")
						.content(asJsonString(new Person("Lara", "Cerqueira", "15 Rue verdi", "Nice", 06000L, "0123456",
								"lara@hotmail.com")))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())
				.andReturn();

		assertEquals(
				"{\"firstName\":\"Lara\",\"lastName\":\"Cerqueira\",\"address\":\"15 Rue verdi\",\"city\":\"Nice\",\"zip\":3072,\"phone\":\"0123456\",\"email\":\"lara@hotmail.com\",\"medicalRecord\":null,\"firestation\":null}",
				mvcResult.getResponse().getContentAsString());
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());

	}

	@Test
	public void updatePerson() throws Exception {

		Person person = new Person("John", "Boyd", "fggf", "Nice", 3072L, "0123456", "lara@hotmail.com");
		Mockito.when(personService.updatePerson(Mockito.any(Person.class))).thenReturn(person);

		mockMvc.perform(MockMvcRequestBuilders.put("/person")
				.content(asJsonString(new Person("John", "Boyd", "fggf", "Nice", 3072L, "0123456", "lara@hotmail.com")))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Boyd"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("fggf"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("Nice"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.zip").value(3072))
				.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("0123456"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("lara@hotmail.com"));
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put("/person")
						.content(asJsonString(
								new Person("John", "Boyd", "fggf", "Nice", (long) 3072, "0123456", "lara@hotmail.com")))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertEquals(
				"{\"firstName\":\"John\",\"lastName\":\"Boyd\",\"address\":\"fggf\",\"city\":\"Nice\",\"zip\":3072,\"phone\":\"0123456\",\"email\":\"lara@hotmail.com\",\"medicalRecord\":null,\"firestation\":null}",
				mvcResult.getResponse().getContentAsString());

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void deletePerson() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete("/person?firstName=Lara&lastName=Cerqueira"))
				.andExpect(status().isOk()).andReturn();
		mockMvc.perform(MockMvcRequestBuilders.delete("/person?firstName=Lara&lastName=Cerqueira")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void findPerson() throws Exception {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		PersonNameEmailDto personNameEmail = new PersonNameEmailDto("John", "Boyd", "lara@hotmail.com", "fggf",
				medications, allergies, 45L);
		List<PersonNameEmailDto> personNameEmailList = new ArrayList<>();
		personNameEmailList.add(personNameEmail);
		PersonByFirstEtLastNameDto personByFirstEtLastName = new PersonByFirstEtLastNameDto(personNameEmailList);
		Mockito.when(personService.getPersonByFirstLastName("John", "Boyd")).thenReturn(personByFirstEtLastName);

		MvcResult mvcResult = mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string(containsString("lastName")))
				.andExpect(content().string(containsString("firstName")))
				.andExpect(content().string(containsString("address")))
				.andExpect(content().string(containsString("medications")))
				.andExpect(content().string(containsString("allergies")))
				.andExpect(content().string(containsString("age"))).andExpect(content().string(containsString("email")))
				.andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void getEmailByCity() throws Exception {
		Person person = new Person("John", "Boyd", "fggf", "Culver", 3072L, "0123456", "lara@hotmail.com");
		String email = person.getEmail();
		List<String> emailList = new ArrayList<>();
		emailList.add(email);
		Mockito.when(personService.getEmailByCity("Culver")).thenReturn(emailList);
		this.mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk())
				.andExpect(content().string(containsString("lara@hotmail.com"))).andReturn();

		MvcResult mvcResult = mockMvc.perform(get("/communityEmail?city=Culver")).andExpect(status().isOk())
				.andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void findPersonByAddress() throws Exception {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		PersonNamePhoneStationDto personNamePhoneStation = new PersonNamePhoneStationDto("John", "Boyd", "45855254",
				medications, allergies, 45L, 1);
		List<PersonNamePhoneStationDto> personNamePhoneStationList = new ArrayList<>();
		personNamePhoneStationList.add(personNamePhoneStation);
		PersonByAddressDto personByAddress = new PersonByAddressDto(personNamePhoneStationList);
		Mockito.when(personService.getPersonsByAdresse("1509 Culver St")).thenReturn(personByAddress);

		this.mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk())
				.andExpect(content().string(containsString("lastName")))
				.andExpect(content().string(containsString("firstName")))
				.andExpect(content().string(containsString("phone")))
				.andExpect(content().string(containsString("medications")))
				.andExpect(content().string(containsString("allergies")))
				.andExpect(content().string(containsString("age")))
				.andExpect(content().string(containsString("station")));

		MvcResult mvcResult = mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk())
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void findChildrenByAddress() throws Exception {
		PersonNameDto personName = new PersonNameDto("John", "Boyd", 45L);
		List<PersonNameDto> personNameList = new ArrayList<>();
		personNameList.add(personName);
		PersonDto person = new PersonDto("Ema", "Boyd");
		List<PersonDto> personList = new ArrayList<>();
		personList.add(person);
		ChildrenByAddressDto childrenByAddress = new ChildrenByAddressDto(personNameList, personList);
		Mockito.when(personService.findChildrenByAddress("1509 Culver St")).thenReturn(childrenByAddress);

		this.mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk())
				.andExpect(content().string(containsString("lastName")))
				.andExpect(content().string(containsString("firstName")))
				.andExpect(content().string(containsString("age")));

		MvcResult mvcResult = mockMvc.perform(get("/childAlert?address=1509 Culver St")).andExpect(status().isOk())
				.andReturn();
		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
