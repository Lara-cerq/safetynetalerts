package com.safety.safetynetalerts.model;

public class PersonNamePhoneDto {
	
	private String firstName;

	private String lastName;

	private String phone;

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
	
	public PersonNamePhoneDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonNamePhoneDto(String firstName, String lastName, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PersonNamePhoneDto [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + "]";
	}

}
