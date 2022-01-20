package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByStationsAndAddressDto {

	private String address;

	private List<PersonNamePhoneDto> personsDto;

	private List<MedicalRecordDto> medicalDto;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PersonNamePhoneDto> getPersonsDto() {
		return personsDto;
	}

	public void setPersonsDto(List<PersonNamePhoneDto> personsDto) {
		this.personsDto = personsDto;
	}

	public List<MedicalRecordDto> getMedicalDto() {
		return medicalDto;
	}

	public void setMedicalDto(List<MedicalRecordDto> medicalDto) {
		this.medicalDto = medicalDto;
	}

	public PersonByStationsAndAddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonByStationsAndAddressDto(String address, List<PersonNamePhoneDto> personsDto,
			List<MedicalRecordDto> medicalDto) {
		super();
		this.address = address;
		this.personsDto = personsDto;
		this.medicalDto = medicalDto;
	}
}
