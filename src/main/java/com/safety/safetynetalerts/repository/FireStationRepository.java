package com.safety.safetynetalerts.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;

public interface FireStationRepository {
	
	public List<FireStation> firestations = new ArrayList<FireStation>();

	public List<FireStation> findAllFirestations();
	
	public List<FireStation> findFirestationByAdress(String address);

	public List<FireStation> findFirestationByStationNumber(Integer station);
	
	public ArrayList<String> findPersonsByStationNumber(Integer station);
	
	public PersonByStationDto findPersonsByStation(Integer station);
	
	public PersonByStationsAndAddressDto findPersonsByStations(List<Integer> stations);
	
	public List<Person> findPersonsByAddress(String address);
	
	public List<String> findPhoneNumberByStationNumber(Integer station);
	
	public FireStation deleteFirestationByAddress(String address);

	public FireStation deleteFirestationByStationNumber(Integer station);
	
	public FireStation updateFirestationByAddress(String address);

	public FireStation saveFirestation(FireStation firestation);

}
