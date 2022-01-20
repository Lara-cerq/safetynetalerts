package com.safety.safetynetalerts.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;
import com.safety.safetynetalerts.repository.FirestationRepositoryImpl;

@Service
public class FirestationService {

	@Autowired
	private FirestationRepositoryImpl firestationRepository;

	public FirestationService(FirestationRepositoryImpl firestationRepository) {
		super();
		this.firestationRepository = firestationRepository;
	}

	public void setFirestationRepository(FirestationRepositoryImpl firestationRepository) {
		this.firestationRepository = firestationRepository;
	}

	public List<FireStation> getAllFirestations() {
		return firestationRepository.findAllFirestations();
	}

	public List<FireStation> getFirestationByAdress(String address) {
		return firestationRepository.findFirestationByAdress(address);
	}

	public List<FireStation> getFirestationByStationNumber(Integer stationNumber) {
		return firestationRepository.findFirestationByStationNumber(stationNumber);
	}

	public ArrayList<String> getPersonsByStationNumber(Integer station) {
		return firestationRepository.findPersonsByStationNumber(station);
	}

	public PersonByStationDto getPersonsByStation(Integer station) {
		return firestationRepository.findPersonsByStation(station);
	}

	public List<String> getPhoneNumbersByStationNumber(Integer station) {
		return firestationRepository.findPhoneNumberByStationNumber(station);
	}
	
	public PersonByStationsAndAddressDto getPersonsByStationAndAddress(List<Integer> stations) {
		return firestationRepository.findPersonsByStations(stations);
	}

}
