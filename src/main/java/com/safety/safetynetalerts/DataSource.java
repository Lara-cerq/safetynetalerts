package com.safety.safetynetalerts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;

import javassist.expr.NewArray;

@Component
public class DataSource {

	private List<Person> persons = new ArrayList<Person>();
	private List<FireStation> firestations = new ArrayList<FireStation>();
	private List<MedicalRecord> medicalrecords = new ArrayList<MedicalRecord>();

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	@Override
	public String toString() {
		return "persons=" + persons + ", firestations=" + firestations + ", medicalrecords=" + medicalrecords;
	}

	@PostConstruct
	private void init() throws IOException {

		// read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("data.json"));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// convert json string to object
		DataSource dataSource = objectMapper.readValue(jsonData, DataSource.class);

		this.persons = dataSource.getPersons();
		this.firestations = dataSource.getFirestations();
		this.medicalrecords = dataSource.getMedicalrecords();

	}

}
