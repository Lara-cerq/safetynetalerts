package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonNamePhoneStationDto {

	private String firstName;

	private String lastName;

	private String phone;

	private List<String> medications;

	private List<String> allergies;

	private Long age;

	private Integer station;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public PersonNamePhoneStationDto(String firstName, String lastName, String phone, List<String> medications,
			List<String> allergies, Long age, Integer station) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.medications = medications;
		this.allergies = allergies;
		this.age = age;
		this.station = station;
	}

	public PersonNamePhoneStationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
