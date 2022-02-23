package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByAddressDto {

	private List<PersonNamePhoneStationDto> personsByAddress;

	public List<PersonNamePhoneStationDto> getPersonDto() {
		return personsByAddress;
	}

	public void setPersonDto(List<PersonNamePhoneStationDto> personsByAddress) {
		this.personsByAddress = personsByAddress;
	}

	public PersonByAddressDto(List<PersonNamePhoneStationDto> personsByAddress) {
		super();
		this.personsByAddress = personsByAddress;
	}

	public PersonByAddressDto() {
		super();
	}
}
