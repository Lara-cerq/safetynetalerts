package com.safety.safetynetalerts.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;
import com.safety.safetynetalerts.model.PersonNameAddressDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;
import com.safety.safetynetalerts.repository.FirestationRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	@InjectMocks
	private FirestationService firestationService;

	@Mock
	private FirestationRepositoryImpl firestationRepository;

	@Test
	public void getAllFirestations() {
		List<FireStation> firestations = new ArrayList<>();
		FireStation firestation = new FireStation("15 Rue Verdi", 1);
		firestations.add(firestation);
		Mockito.when(firestationRepository.findAllFirestations()).thenReturn(firestations);
		List<FireStation> firestationsresultat = firestationService.getAllFirestations();
		assertEquals(firestationsresultat, firestations);
	}

	@Test
	public void saveFirestations() {
		FireStation firestation = new FireStation("15 Rue Verdi", 1);
		Mockito.when(firestationRepository.saveFirestation(firestation)).thenReturn(firestation);
		FireStation firestationResultat = firestationService.saveFirestation(firestation);
		assertEquals(firestationResultat, firestation);
	}

	@Test
	public void updateFirestation() {
		FireStation firestation = new FireStation("15 Rue Verdi", 1);
		Mockito.when(firestationRepository.updateFirestationByAddress(firestation)).thenReturn(firestation);
		FireStation firestationResultat = firestationService.updateFirestationByAddress(firestation);
		assertEquals(firestationResultat, firestation);
	}

	@Test
	public void deleteFirestationByStation() {
		Mockito.when(firestationRepository.deleteFirestationByStation(1)).thenReturn(true);
		boolean firestationResultat = firestationService.deleteFirestationByStation(1);
		assertEquals(firestationResultat, true);
	}

	@Test
	public void deleteFirestationByAddress() {
		Mockito.when(firestationRepository.deleteFirestationByAddress("15 Rue Verdi")).thenReturn(true);
		boolean firestationResultat = firestationService.deleteFirestationByAddress("15 Rue Verdi");
		assertEquals(firestationResultat, true);
	}

	@Test
	public void getPersonsByStation() {
		PersonNameAddressDto personNameAddress = new PersonNameAddressDto("Lara", "Cerqueira", "0125545", "Nice");
		List<PersonNameAddressDto> personNamePhoneList = new ArrayList<>();
		personNamePhoneList.add(personNameAddress);
		PersonByStationDto personByStation = new PersonByStationDto(personNamePhoneList, 1, 1);
		Mockito.when(firestationRepository.findPersonsByStation(1)).thenReturn(personByStation);
		PersonByStationDto personByStationResultat = firestationService.getPersonsByStation(1);
		assertEquals(personByStation, personByStationResultat);
	}

	@Test
	public void getPhoneNumbersByStationNumber() {
		Person person = new Person("Lara", "Cerqueira", "Nice", "Nice", 06000L, "0123225", "lksj@hot.com");
		List<String> phoneNumbersList = new ArrayList<>();
		String phone = person.getPhone();
		phoneNumbersList.add(phone);
		Mockito.when(firestationRepository.findPhoneNumberByStationNumber(1)).thenReturn(phoneNumbersList);
		List<String> phoneNumbersListResultat = firestationService.getPhoneNumbersByStationNumber(1);
		assertEquals(phoneNumbersList, phoneNumbersListResultat);
	}

	@Test
	public void getPersonsByStationAndAddress() {
		Person person = new Person("Lara", "Cerqueira", "15 Rue Verdi", "Nice", 06000L, "0123225", "lksj@hot.com");
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		PersonNamePhoneDto personNamePhoneDto = new PersonNamePhoneDto("Lara", "Cerqueira", "0123225", "15 Rue Verdi",
				medications, allergies, 18L);
		String address = person.getAddress();
		List<PersonNamePhoneDto> personNamePhoneList = new ArrayList<>();
		personNamePhoneList.add(personNamePhoneDto);
		PersonByStationsAndAddressDto personStationDto = new PersonByStationsAndAddressDto(address,
				personNamePhoneList);
		Map<String, List<PersonNamePhoneDto>> personNamePhoneListByAddress = new HashMap<>();
		personNamePhoneListByAddress.put(address, personStationDto.getPersonsDto());
		List<Integer> stationsNumbersList = new ArrayList<>();
		stationsNumbersList.add(1);
		Mockito.when(firestationRepository.findPersonsByStations(stationsNumbersList))
				.thenReturn(personNamePhoneListByAddress);
		Map<String, List<PersonNamePhoneDto>> personNamePhoneListByAddressResult = firestationService
				.getPersonsByStationAndAddress(stationsNumbersList);
		assertEquals(personNamePhoneListByAddress, personNamePhoneListByAddressResult);
	}

}
