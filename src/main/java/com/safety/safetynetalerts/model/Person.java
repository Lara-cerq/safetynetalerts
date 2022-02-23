package com.safety.safetynetalerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Person {

	@EqualsAndHashCode.Include
	private String firstName;

	@EqualsAndHashCode.Include
	private String lastName;

	private String address;

	private String city;

	private Long zip;

	private String phone;

	private String email;

	private MedicalRecord medicalRecord;

	private FireStation firestation;

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String address, String city, Long zip, String phone,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
}
