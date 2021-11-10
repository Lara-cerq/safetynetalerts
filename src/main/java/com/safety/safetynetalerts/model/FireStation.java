package com.safety.safetynetalerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class FireStation {

	private Long idFireStations;

	private String address;

	private Integer station;

	public Long getIdFireStations() {
		return idFireStations;
	}

	public void setIdFireStations(Long idFireStations) {
		this.idFireStations = idFireStations;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStation() {
		return station;
	}

	public void setStation(Integer station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "address=" + address + ", station=" + station;
	}
}
