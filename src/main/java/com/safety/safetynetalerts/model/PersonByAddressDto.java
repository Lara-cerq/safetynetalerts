package com.safety.safetynetalerts.model;

import java.util.List;

public class PersonByAddressDto {

	private List<PersonNamePhoneStationDto> persons;

	private Integer station;

	public List<PersonNamePhoneStationDto> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonNamePhoneStationDto> persons) {
		this.persons = persons;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	public PersonByAddressDto(List<PersonNamePhoneStationDto> persons, Integer station) {
		super();
		this.persons = persons;
		this.station = station;
	}

	public PersonByAddressDto() {
		super();
	}
}
