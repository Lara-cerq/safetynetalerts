package com.safety.safetynetalerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class FireStation {

	@EqualsAndHashCode.Include
	private String address;

	private Integer station;

	private Person person;

	private MedicalRecord medicalRecord;

	public FireStation() {
		super();
	}

	public FireStation(String address, Integer station) {
		super();
		this.address = address;
		this.station = station;
	}

}
