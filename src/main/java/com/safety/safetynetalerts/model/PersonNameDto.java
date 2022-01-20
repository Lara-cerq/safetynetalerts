package com.safety.safetynetalerts.model;

public class PersonNameDto {

	private String firstName;

	private String lastName;

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

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public PersonNameDto() {
		super();
	}

	public PersonNameDto(String firstName, String lastName, Long age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonNameDto [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
	}

}
