package com.safety.safetynetalerts.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;
import com.safety.safetynetalerts.model.PersonNameAddressDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FirestationRepositoryTest {

	@Autowired
	private FirestationRepositoryImpl firestationRepository;
	@Autowired
	private DataSource dataSource;

	@Test
	public void contextLoads() {
		assertNotNull(firestationRepository);
	}

	@Test
	public void getAllFirestations() {
		List<FireStation> firestations = firestationRepository.findAllFirestations();
		List<FireStation> firestationsResult = dataSource.getFirestations();
		assertNotNull(firestations);
		assertNotNull(firestationsResult);
		assertEquals(firestations, firestationsResult);
	}

	@Test
	public void deleteFirestationsByAddress() {
		boolean isRemoved = firestationRepository.deleteFirestationByAddress("1509 Culver St");
		assertEquals(isRemoved, true);
	}

	@Test
	public void deleteFirestationsByStation() {
		boolean isRemoved = firestationRepository.deleteFirestationByStation(1);
		assertEquals(isRemoved, true);
	}

	@Test
	public void saveFirestation() {
		List<FireStation> firestations = dataSource.getFirestations();
		FireStation firestation = new FireStation("15 Rue Verdi", 1);
		FireStation firestationResult = firestationRepository.saveFirestation(firestation);
		boolean result = firestations.contains(firestationResult);
		assertEquals(result, true);
	}

	@Test
	public void updateFirestation() {
		List<FireStation> firestations = dataSource.getFirestations();
		FireStation firestation = new FireStation("29 15th St", 3);
		FireStation firestationResult = firestationRepository.updateFirestationByAddress(firestation);
		boolean result = firestations.contains(firestationResult);
		assertEquals(result, true);
	}

	@Test
	public void findPhoneNumberByStationNumber() {
		List<String> phoneNumbersList = firestationRepository.findPhoneNumberByStationNumber(2);
		boolean result = phoneNumbersList.contains("841-874-7512");
		assertEquals(result, true);
	}

	@Test
	public void findPersonsByStation() {
		PersonNameAddressDto personDto = new PersonNameAddressDto("Lily", "Cooper", "489 Manchester St",
				"841-874-9845");
		PersonNameAddressDto personDto2 = new PersonNameAddressDto("Tony", "Cooper", "112 Steppes Pl", "841-874-6874");
		PersonNameAddressDto personDto3 = new PersonNameAddressDto("Ron", "Peters", "112 Steppes Pl", "841-874-8888");
		PersonNameAddressDto personDto4 = new PersonNameAddressDto("Allison", "Boyd", "112 Steppes Pl", "841-874-9888");
		List<PersonNameAddressDto> personDtoList = new ArrayList<PersonNameAddressDto>();
		personDtoList.add(personDto);
		personDtoList.add(personDto2);
		personDtoList.add(personDto3);
		personDtoList.add(personDto4);
		PersonByStationDto personByStation = new PersonByStationDto(personDtoList, 4, 0);
		PersonByStationDto personByStationResult = firestationRepository.findPersonsByStation(4);
		assertNotNull(personByStationResult);
		assertEquals(personByStationResult.toString(), personByStation.toString());
	}

	@Test
	public void findPersonsByStations() {
		List<Integer> listStations = new ArrayList<>();
		listStations.add(2);
		Person person = new Person("Eric", "Cadigan", "951 LoneTree Rd", "Culver", 97451L, "841-874-7458",
				"gramps@email.com");
		List<String> medications = new ArrayList<>();
		medications.add("tradoxidine:400mg");
		List<String> allergies = new ArrayList<>();
		PersonNamePhoneDto personNamePhoneDto = new PersonNamePhoneDto("Eric", "Cadigan", "841-874-7458",
				"951 LoneTree Rd", medications, allergies, 76L);
		String address = person.getAddress();
		List<PersonNamePhoneDto> personNamePhoneList = new ArrayList<>();
		personNamePhoneList.add(personNamePhoneDto);
		PersonByStationsAndAddressDto personStationDto = new PersonByStationsAndAddressDto(address,
				personNamePhoneList);
		Map<String, List<PersonNamePhoneDto>> personsByAddress = new HashMap<>();
		personsByAddress.put(address, personStationDto.getPersonsDto());
		Map<String, List<PersonNamePhoneDto>> personsByAddressResult = firestationRepository
				.findPersonsByStations(listStations);
		String key = "";
		List<PersonNamePhoneDto> value = new ArrayList<>();
		for (Map.Entry<String, List<PersonNamePhoneDto>> entry : personsByAddress.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
		}
		boolean result = value.contains(personNamePhoneDto);
		boolean resultAddress = key.contains(address);
		assertEquals(result, true);
		assertEquals(resultAddress, true);
		assertNotNull(personsByAddressResult);
	}
}
