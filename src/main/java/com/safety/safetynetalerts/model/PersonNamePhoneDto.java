package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonNamePhoneDto {

	private String firstName;

	private String lastName;

	private String phone;
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public PersonNamePhoneDto(String firstName, String lastName, String phone, String address, List<String> medications,
			List<String> allergies, Long age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.medications = medications;
		this.allergies = allergies;
		this.age = age;
	}

	public PersonNamePhoneDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
