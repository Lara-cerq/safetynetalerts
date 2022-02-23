package com.safety.safetynetalerts.model;

import java.util.List;

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
public class MedicalRecord {

	@EqualsAndHashCode.Include
	private String firstName;
	
	@EqualsAndHashCode.Include
	private String lastName;

	private String birthdate;
	
	private List<String> medications;

	private List<String> allergies;
	
	public MedicalRecord() {
		super();
	}
}
