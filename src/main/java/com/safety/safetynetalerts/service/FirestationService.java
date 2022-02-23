package com.safety.safetynetalerts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;
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
	
	public FireStation saveFirestation(FireStation firestation) {
		return firestationRepository.saveFirestation(firestation);
	}
	
	public boolean deleteFirestationByAddress(String address) {
		return firestationRepository.deleteFirestationByAddress(address);
	}
	
	public boolean deleteFirestationByStation(Integer station) {
		return firestationRepository.deleteFirestationByStation(station);
	}

	public FireStation updateFirestationByAddress(FireStation firestation) {
		return firestationRepository.updateFirestationByAddress(firestation);
	}

	public PersonByStationDto getPersonsByStation(Integer station) {
		return firestationRepository.findPersonsByStation(station);
	}

	public List<String> getPhoneNumbersByStationNumber(Integer station) {
		return firestationRepository.findPhoneNumberByStationNumber(station);
	}

	public Map<String, List<PersonNamePhoneDto>> getPersonsByStationAndAddress(List<Integer> stations) {
		return firestationRepository.findPersonsByStations(stations);
	}

}
