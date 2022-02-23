package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByStationDto {

	private List<PersonNameAddressDto> persons;

	private Integer numberAdult;

	private Integer numberChildren;

	public List<PersonNameAddressDto> getPersonDto() {
		return persons;
	}

	public void setPersonDto(List<PersonNameAddressDto> personDto) {
		this.persons = personDto;
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
		this.persons = personDto;
		this.numberAdult = numberAdult;
		this.numberChildren = numberChildren;
	}

	public PersonByStationDto() {
		super();
	}

	@Override
	public String toString() {
		return "PersonByStationDto [personDto=" + persons + ", numberAdult=" + numberAdult + ", numberChildren="
				+ numberChildren + "]";
	}

}
