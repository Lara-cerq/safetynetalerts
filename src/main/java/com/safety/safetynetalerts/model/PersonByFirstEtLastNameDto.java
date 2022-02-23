package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByFirstEtLastNameDto {

	private List<PersonNameEmailDto> personDto;

	public List<PersonNameEmailDto> getPersonDto() {
		return personDto;
	}

	public void setPersonDto(List<PersonNameEmailDto> personDto) {
		this.personDto = personDto;
	}

	public PersonByFirstEtLastNameDto() {
		super();
	}

	public PersonByFirstEtLastNameDto(List<PersonNameEmailDto> personDto) {
		super();
		this.personDto = personDto;
	}

}
