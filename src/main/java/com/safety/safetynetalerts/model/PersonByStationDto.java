package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByStationDto {

	private List<PersonNameAddressDto> persons;

	private Integer numberAdult;

	private Integer numberChildren;

	public List<PersonNameAddressDto> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonNameAddressDto> persons) {
		this.persons = persons;
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

	public PersonByStationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonByStationDto(List<PersonNameAddressDto> persons, Integer numberAdult, Integer numberChildren) {
		super();
		this.persons = persons;
		this.numberAdult = numberAdult;
		this.numberChildren = numberChildren;
	}

	@Override
	public String toString() {
		return "PersonByStationDto [persons=" + persons + ", numberAdult=" + numberAdult + ", numberChildren="
				+ numberChildren + "]";
	}

}
