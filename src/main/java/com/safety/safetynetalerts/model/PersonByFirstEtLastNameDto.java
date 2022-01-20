package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByFirstEtLastNameDto {

	private List<PersonNameEmailDto> personDto;

	private List<MedicalRecordDto> medicalRecordDto;


	public List<PersonNameEmailDto> getPersonDto() {
		return personDto;
	}

	public void setPersonDto(List<PersonNameEmailDto> personDto) {
		this.personDto = personDto;
	}

	public List<MedicalRecordDto> getMedicalRecordDto() {
		return medicalRecordDto;
	}

	public void setMedicalRecordDto(List<MedicalRecordDto> medicalRecordDto) {
		this.medicalRecordDto = medicalRecordDto;
	}

	public PersonByFirstEtLastNameDto(List<PersonNameEmailDto> personDto, List<MedicalRecordDto> medicalRecordDto) {
		super();
		this.personDto = personDto;
		this.medicalRecordDto = medicalRecordDto;
	}

	public PersonByFirstEtLastNameDto() {
		super();
	}

	@Override
	public String toString() {
		return "PersonByFirstEtLastNameDto [personDto=" + personDto + ", medicalRecordDto=" + medicalRecordDto + "]";
	}

}
