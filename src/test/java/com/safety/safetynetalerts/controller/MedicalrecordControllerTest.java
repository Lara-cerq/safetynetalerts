package com.safety.safetynetalerts.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynetalerts.SafetynetalertsApplication;
import com.safety.safetynetalerts.controller.MedicalRecordController;
import com.safety.safetynetalerts.controller.PersonController;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;

@WebMvcTest(MedicalRecordController.class)
@ComponentScan(basePackages = "com.safety")
//@SpringBootTest
//@AutoConfigureMockMvc
public class MedicalrecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetMedicalrecord() throws Exception {
		mockMvc.perform(get("/medicalrecords")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.valueOf("application/json")))
				.andExpect(content().string(containsString("John")));
	}

	@Test
	public void testSaveMedicalrecord() throws Exception {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		mockMvc.perform(MockMvcRequestBuilders.post("/medicalrecord")
				.content(asJsonString(new MedicalRecord("Lara", "Cerqueira", "28/10/1993", medications, allergies)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
	}

	@Test
	public void updateMedicalrecord() throws Exception {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		mockMvc.perform(MockMvcRequestBuilders.put("/medicalrecord")
				.content(asJsonString(new MedicalRecord("John", "Boyd", "03/06/1984", medications, allergies)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Boyd"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.birthdate").value("03/06/1984"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.medications").value(medications))
				.andExpect(MockMvcResultMatchers.jsonPath("$.allergies").value(allergies));
	}

	@Test
	public void deletePerson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/medicalrecord?firstName=Lara&lastName=Cerqueira"))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
