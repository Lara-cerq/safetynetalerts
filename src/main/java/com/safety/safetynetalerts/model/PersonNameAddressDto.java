package com.safety.safetynetalerts.model;

public class PersonNameAddressDto {

	private String firstName;

	private String lastName;

	private String address;

	private String phone;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public PersonNameAddressDto() {
		super();
	}

	public PersonNameAddressDto(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PersonDto [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phone="
				+ phone + "]";
	}
}
