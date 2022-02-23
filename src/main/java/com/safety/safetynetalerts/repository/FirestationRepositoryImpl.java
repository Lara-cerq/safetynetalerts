package com.safety.safetynetalerts.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;
import com.safety.safetynetalerts.model.PersonNameAddressDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;

@Repository
public class FirestationRepositoryImpl implements FireStationRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<FireStation> findAllFirestations() {
		return dataSource.getFirestations();
	}

	@Override
	public boolean deleteFirestationByAddress(String address) {
		List<FireStation> firestations = findAllFirestations();
		boolean isRemoved = firestations.removeIf(firestation -> firestation.getAddress().equals(address));
		return isRemoved;
	}

	@Override
	public FireStation saveFirestation(FireStation firestation) {
		List<FireStation> firestations = findAllFirestations();
		firestations.add(firestation);
		return firestation;
	}

	@Override
	public boolean deleteFirestationByStation(Integer station) {
		List<FireStation> firestations = findAllFirestations();
		boolean isRemoved = firestations.removeIf(firestation -> firestation.getStation().equals(station));
		return isRemoved;
	}

	@Override
	public FireStation updateFirestationByAddress(FireStation firestation) {
		List<FireStation> firestations = findAllFirestations();
		int index = firestations.indexOf(firestation);
		firestations.set(index, firestation);
		return firestation;
	}

	@Override
	public List<String> findPhoneNumberByStationNumber(Integer station) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<Person> persons = dataSource.getPersons();
		List<String> phoneNumbersList = new ArrayList<String>();
		for (FireStation firestation : firestations) {
			for (Person person : persons) {
				if (firestation.getAddress().equals(person.getAddress())) {
					firestation.setPerson(person);
					if (firestation.getStation().equals(station)) {
						Person person2 = firestation.getPerson();
						String phoneNumber = person2.getPhone();
						phoneNumbersList.add(phoneNumber);
					}
				}
			}
		}
		return phoneNumbersList;
	}

	@Override
	public PersonByStationDto findPersonsByStation(Integer station) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		PersonNameAddressDto personDto = new PersonNameAddressDto();
		List<PersonNameAddressDto> personDtoList = new ArrayList<PersonNameAddressDto>();
		PersonByStationDto personByStationDto = new PersonByStationDto();
		int childCounter = 0;
		int adultCounter = 0;
		for (FireStation firestation : firestations) {
			for (Person person : persons) {
				for (MedicalRecord medicalRecord : medicalrecords) {
					if (firestation.getAddress().equals(person.getAddress())) {
						firestation.setPerson(person);
						if (person.getFirstName().equals(medicalRecord.getFirstName())
								&& person.getLastName().equals(medicalRecord.getLastName())) {
							person.setMedicalRecord(medicalRecord);
							if (firestation.getStation().equals(station)) {
								Person person2 = firestation.getPerson();
								MedicalRecord medicalRecord2 = person.getMedicalRecord();
								String firstName = person2.getFirstName();
								String lastName = person2.getLastName();
								String adresse = person2.getAddress();
								String phone = person2.getPhone();
								personDto = new PersonNameAddressDto(firstName, lastName, adresse, phone);
								personDtoList.add(personDto);
								LocalDate birthDate = LocalDate.parse(medicalRecord2.getBirthdate(),
										DateTimeFormatter.ofPattern("MM/dd/yyyy"));
								LocalDate now = LocalDate.now();
								long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
								if (age < 18) {
									childCounter++;
								} else {
									adultCounter++;
								}
								personByStationDto = new PersonByStationDto(personDtoList, adultCounter, childCounter);
							}
						}
					}
				}
			}
		}
		return personByStationDto;
	}

	@Override
	public Map<String, List<PersonNamePhoneDto>> findPersonsByStations(List<Integer> stations) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		PersonNamePhoneDto personDto = new PersonNamePhoneDto();
		List<PersonNamePhoneDto> personDtoList = new ArrayList<PersonNamePhoneDto>();
		PersonByStationsAndAddressDto personStationDto = new PersonByStationsAndAddressDto();
		Map<String, List<PersonNamePhoneDto>> returnValue = new HashMap<>();
		String address = "";
		for (FireStation firestation : firestations) {
			for (Person person : persons) {
				for (MedicalRecord medicalRecord : medicalrecords) {
					if (person.getFirstName().equals(medicalRecord.getFirstName())
							&& person.getLastName().equals(medicalRecord.getLastName())) {
						person.setMedicalRecord(medicalRecord);
						if (firestation.getAddress().equals(person.getAddress())) {
							person.setFirestation(firestation);
							for (Integer station : stations) {
								if (firestation.getStation().equals(station)) {
									address = firestation.getAddress();
									MedicalRecord medicalRecord2 = person.getMedicalRecord();
									String firstName = person.getFirstName();
									String lastName = person.getLastName();
									String phone = person.getPhone();
									List<String> allergies = medicalRecord2.getAllergies();
									List<String> medications = medicalRecord2.getMedications();
									LocalDate birthDate = LocalDate.parse(medicalRecord2.getBirthdate(),
											DateTimeFormatter.ofPattern("MM/dd/yyyy"));
									LocalDate now = LocalDate.now();
									long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
									personDto = new PersonNamePhoneDto(firstName, lastName, phone, address, medications,
											allergies, age);
									personDtoList.add(personDto);
									Map<String, List<PersonNamePhoneDto>> groupeeParAddress = personDtoList.stream()
											.collect(Collectors.groupingBy(PersonNamePhoneDto::getAddress));
									Map<String, List<PersonNamePhoneDto>> mapByAddress = new HashMap<String, List<PersonNamePhoneDto>>();
									for (Map.Entry<String, List<PersonNamePhoneDto>> mapentry : groupeeParAddress
											.entrySet()) {
										if (!mapByAddress.containsKey(mapentry.getKey())) {
											mapByAddress.put(mapentry.getKey(), mapentry.getValue());
										}
									}
									for (Map.Entry<String, List<PersonNamePhoneDto>> mapentry : mapByAddress
											.entrySet()) {
										if (mapentry.getKey().equals(address)) {
											List<PersonNamePhoneDto> personsList = new ArrayList<>();
											personsList = mapentry.getValue();
											personStationDto = new PersonByStationsAndAddressDto(address, personsList);
											returnValue.put(address, personStationDto.getPersonsDto());

										}
									}
								}

							}
						}
					}
				}
			}
		}
		return returnValue;
	}
}