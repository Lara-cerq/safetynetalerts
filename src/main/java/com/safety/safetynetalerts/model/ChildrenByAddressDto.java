package com.safety.safetynetalerts.model;

import java.util.List;

public class ChildrenByAddressDto {

	private List<PersonNameDto> personNameDto;

	private List<PersonDto> personDto;

	public List<PersonNameDto> getPersonNameDto() {
		return personNameDto;
	}

	public void setPersonNameDto(List<PersonNameDto> personNameDto) {
		this.personNameDto = personNameDto;
	}

	public List<PersonDto> getPersonDto() {
		return personDto;
	}

	public void setPersonDto(List<PersonDto> personDto) {
		this.personDto = personDto;
	}

	public ChildrenByAddressDto() {
		super();
	}

	public ChildrenByAddressDto(List<PersonNameDto> personNameDto, List<PersonDto> personDto) {
		super();
		this.personNameDto = personNameDto;
		this.personDto = personDto;
	}

	@Override
	public String toString() {
		return "ChildrenByAddressDto [personNameDto=" + personNameDto + ", personDto=" + personDto + "]";
	}

}
