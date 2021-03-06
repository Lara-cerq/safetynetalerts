package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByFirstEtLastNameDto {

	private List<PersonNameEmailDto> persons;

	public List<PersonNameEmailDto> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonNameEmailDto> persons) {
		this.persons = persons;
	}

	public PersonByFirstEtLastNameDto(List<PersonNameEmailDto> persons) {
		super();
		this.persons = persons;
	}

	public PersonByFirstEtLastNameDto() {
		super();
	}

}
