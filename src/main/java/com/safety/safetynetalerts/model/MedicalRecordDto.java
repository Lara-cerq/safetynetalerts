package com.safety.safetynetalerts.model;

import java.util.List;

public class MedicalRecordDto {

	private List<String> medications;

	private List<String> allergies;

	private Long age;

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public List<String> getMedications() {
		return medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public Long getAge() {
		return age;
	}

	public MedicalRecordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MedicalRecordDto(List<String> medications, List<String> allergies, Long age) {
		super();
		this.medications = medications;
		this.allergies = allergies;
		this.age = age;
	}

	@Override
	public String toString() {
		return "MedicalRecordDto [medications=" + medications + ", allergies=" + allergies + ", age=" + age + "]";
	}

}
