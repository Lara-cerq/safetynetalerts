package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByStationDto {

	private List<PersonNameAddressDto> personDto;

	private Integer numberAdult;

	private Integer numberChildren;

	public List<PersonNameAddressDto> getPersonDto() {
		return personDto;
	}

	public void setPersonDto(List<PersonNameAddressDto> personDto) {
		this.personDto = personDto;
	}

	public Integer getNumberAdult() {
		return numberAdult;
	}

	public void setNumberAdult(Integer numberAdult) {
		this.numberAdult = numberAdult;
	}

	public Integer getNumberChildren() {
		return numberChildren;
	}

	public void setNumberChildren(Integer numberChildren) {
		this.numberChildren = numberChildren;
	}

	public PersonByStationDto(List<PersonNameAddressDto> personDto, int numberAdult, int numberChildren) {
		super();
		this.personDto = personDto;
		this.numberAdult = numberAdult;
		this.numberChildren = numberChildren;
	}

	public PersonByStationDto() {
		super();
	}

	@Override
	public String toString() {
		return "PersonByStationDto [personDto=" + personDto + ", numberAdult=" + numberAdult + ", numberChildren="
				+ numberChildren + "]";
	}

}
