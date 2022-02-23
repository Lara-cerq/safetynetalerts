package com.safety.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynetalerts.controller.FirestationController;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonNameAddressDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;
import com.safety.safetynetalerts.repository.FirestationRepositoryImpl;
import com.safety.safetynetalerts.service.FirestationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FirestationController.class)
public class FirestationControllerTest {

	@MockBean
	private FirestationService firestationService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetFirestations() throws Exception {
		List<FireStation> firestations = new ArrayList<>();
		FireStation firestation = new FireStation("1509 Culver St", 3);
		firestations.add(firestation);
		Mockito.when(firestationService.getAllFirestations()).thenReturn(firestations);

		mockMvc.perform(get("/allfirestations")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.valueOf("application/json")))
				.andExpect(content().string(containsString("1509 Culver St")));

		MvcResult mvcResult = mockMvc.perform(get("/allfirestations")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.valueOf("application/json")))
				.andExpect(content().string(containsString("1509 Culver St"))).andReturn();

		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void testSaveFirestation() throws Exception {
		FireStation firestation = new FireStation("1509 Culver St", 3);
		Mockito.when(firestationService.saveFirestation(Mockito.any(FireStation.class))).thenReturn(firestation);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/firestation")
						.content(asJsonString(new FireStation("1509 Culver St", 3)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").exists()).andExpect(status().isCreated())
				.andReturn();
		assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void updateFirestation() throws Exception {
		FireStation firestation = new FireStation("1509 Culver St", 2);
		Mockito.when(firestationService.updateFirestationByAddress(Mockito.any(FireStation.class)))
				.thenReturn(firestation);

//		mockMvc.perform(
//				MockMvcRequestBuilders.put("/firestation").content(asJsonString(new FireStation("1509 Culver St", 2)))
//						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1509 Culver St"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.station").value(2));

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.put("/firestation")
						.content(asJsonString(new FireStation("1509 Culver St", 2)))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("1509 Culver St"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.station").value(2)).andReturn();

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	public void deleteFirestationByAddress() throws Exception {
		Mockito.when(firestationService.deleteFirestationByAddress("1509 Culver St")).thenReturn(true);

		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete("/firestation?address=1509 Culver St")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
//
//		mockMvc.perform(MockMvcRequestBuilders.delete("/firestation?address=15 Rue Verdi")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void deleteFirestationByStation() throws Exception {
		Mockito.when(firestationService.deleteFirestationByStation(1)).thenReturn(true);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.delete("/firestation/station?station=1")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

//		mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/station?station=1")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void findPhoneNumberByStation() throws Exception {
		Person person = new Person("Lara", "Cerqueira", "15 Rue verdi", "Nice", 06000L, "0123456", "lara@hotmail.com");
		List<String> phoneNumbersList = new ArrayList<>();
		String phoneNumber = person.getPhone();
		phoneNumbersList.add(phoneNumber);
		Mockito.when(firestationService.getPhoneNumbersByStationNumber(1)).thenReturn(phoneNumbersList);

		mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(content().string(containsString("0123456")))
				.andExpect(status().isOk());
	}

	@Test
	public void getPersonsByStation() throws Exception {
		PersonNameAddressDto personNameAddress = new PersonNameAddressDto("Lara", "Cerqueira", "15 Rue Verdi",
				"0123456789");
		List<PersonNameAddressDto> personNameAddressList = new ArrayList<>();
		personNameAddressList.add(personNameAddress);
		PersonByStationDto personByStation = new PersonByStationDto(personNameAddressList, 1, 0);
		Mockito.when(firestationService.getPersonsByStation(2)).thenReturn(personByStation);

		mockMvc.perform(MockMvcRequestBuilders.get("/firestations?stationNumber=2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Lara"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Cerqueira"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("15 Rue Verdi"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("0123456789"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.numberAdult").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.numberChildren").value(0));

	}
	
	@Test
	public void findPersonsByListStations() throws Exception {
		List<Integer> stationsList=new ArrayList<>();
		stationsList.add(1);
		List<String> medications=new ArrayList<>();
		List<String> allergies=new ArrayList<>();
		List<PersonNamePhoneDto> personNameList=new ArrayList<>();
		PersonNamePhoneDto personNamePhone= new PersonNamePhoneDto("Lara", "Cerqueira", "0133466788", "15 Rue Verdi", medications, allergies, 25L);
		personNameList.add(personNamePhone);
		Map<String, List<PersonNamePhoneDto>> personByAddress= new HashMap<>();
		personByAddress.put("15 Rue Verdi", personNameList);
		Mockito.when(firestationService.getPersonsByStationAndAddress(stationsList)).thenReturn(personByAddress);
	
		mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations=1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(content().string(containsString("15 Rue Verdi")))
				.andExpect(content().string(containsString("Lara")));

	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
