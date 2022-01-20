package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByAddressDto {

	private List<PersonNamePhoneDto> personDto;

	private List<MedicalRecordDto> medicalRecordDto;

	private Integer station;

	public List<PersonNamePhoneDto> getPersonDto() {
		return personDto;
	}

	public void setPersonDto(List<PersonNamePhoneDto> personDto) {
		this.personDto = personDto;
	}

	public List<MedicalRecordDto> getMedicalRecordDto() {
		return medicalRecordDto;
	}

	public void setMedicalRecordDto(List<MedicalRecordDto> medicalRecordDto) {
		this.medicalRecordDto = medicalRecordDto;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public PersonByAddressDto(List<PersonNamePhoneDto> personDto, List<MedicalRecordDto> medicalRecordDto,
			Integer station) {
		super();
		this.personDto = personDto;
		this.medicalRecordDto = medicalRecordDto;
		this.station = station;
	}

	public PersonByAddressDto() {
		super();
	}

	@Override
	public String toString() {
		return "PersonByAddressDto [personDto=" + personDto + ", medicalRecordDto=" + medicalRecordDto + ", station="
				+ station + "]";
	}

}
