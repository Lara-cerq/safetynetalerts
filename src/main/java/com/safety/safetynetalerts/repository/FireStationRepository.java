package com.safety.safetynetalerts.repository;

import java.util.List;
import java.util.Map;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;

public interface FireStationRepository {

	public List<FireStation> findAllFirestations();
	
	public FireStation saveFirestation(FireStation firestation);

	public PersonByStationDto findPersonsByStation(Integer station);
	
	public Map<String, List<PersonNamePhoneDto>>  findPersonsByStations(List<Integer> stations);

	public List<String> findPhoneNumberByStationNumber(Integer station);

	public boolean deleteFirestationByAddress(String address);
	
	public boolean deleteFirestationByStation(Integer station);

	public FireStation updateFirestationByAddress(FireStation firestation);

}
