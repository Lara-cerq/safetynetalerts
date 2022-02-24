package com.safety.safetynetalerts.model;

import java.util.List;

public class ChildrenByAddressDto {

	private List<PersonNameDto> childrens;

	private List<PersonDto> persons;

	public List<PersonNameDto> getChildrens() {
		return childrens;
	}

	public void setPersonNameDto(List<PersonNameDto> childrens) {
		this.childrens = childrens;
	}

	public List<PersonDto> getPersons() {
		return persons;
	}

	public void setPersonDto(List<PersonDto> persons) {
		this.persons = persons;
	}

	public ChildrenByAddressDto() {
		super();
	}

	public ChildrenByAddressDto(List<PersonNameDto> childrens, List<PersonDto> persons) {
		super();
		this.childrens = childrens;
		this.persons = persons;
	}
}
