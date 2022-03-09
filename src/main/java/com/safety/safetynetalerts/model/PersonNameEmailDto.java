package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonNameEmailDto {

	private String firstName;

	private String lastName;

	private String email;

	private String address;

	private List<String> medications;

	private List<String> allergies;

	private Long age;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public PersonNameEmailDto(String firstName, String lastName, String email, String address, List<String> medications,
			List<String> allergies, Long age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.medications = medications;
		this.allergies = allergies;
		this.age = age;
	}

	public PersonNameEmailDto() {
		super();
	}

}
