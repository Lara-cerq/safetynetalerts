package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByFirstEtLastNameDto {

	private List<PersonNameEmailDto> persons;

	public List<PersonNameEmailDto> getPersons() {
		return persons;
	}

	public void setPersonDto(List<PersonNameEmailDto> persons) {
		this.persons = persons;
	}

	public PersonByFirstEtLastNameDto() {
		super();
	}

	public PersonByFirstEtLastNameDto(List<PersonNameEmailDto> persons) {
		super();
		this.persons = persons;
	}

}
