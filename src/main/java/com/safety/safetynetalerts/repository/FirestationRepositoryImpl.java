package com.safety.safetynetalerts.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.MedicalRecordDto;
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
	public List<FireStation> findFirestationByAdress(String address) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<FireStation> firestationsResult = new ArrayList<FireStation>();
		for (FireStation firestation : firestations) {
			if (firestation.getStation().equals(address)) {
				firestationsResult.add(firestation);
			}
		}
		return firestationsResult;
	}

	@Override
	public List<FireStation> findFirestationByStationNumber(Integer station) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<FireStation> firestationsResult = new ArrayList<FireStation>();
		for (FireStation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				firestationsResult.add(firestation);
			}
		}
		return firestationsResult;
	}

	@Override
	public FireStation deleteFirestationByAddress(String address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FireStation deleteFirestationByStationNumber(Integer station) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FireStation updateFirestationByAddress(String address) {
		// changer numero de caserne par address
		return null;
	}

	@Override
	public FireStation saveFirestation(FireStation firestation) {
		// ajout caserne+address
		return null;
	}

	@Override
	public List<Person> findPersonsByAddress(String address) {
		List<Person> persons = dataSource.getPersons();
		List<Person> personList = new ArrayList<Person>();
		for (Person person1 : persons) {
			if (person1.getAddress().equals(address)) {
				personList.add(person1);
			}
		}
		return personList;
	}

	@Override
	public ArrayList<String> findPersonsByStationNumber(Integer station) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		ArrayList<String> allInformations = new ArrayList<String>();
		List<LocalDate> listMinor = new ArrayList<LocalDate>();
		List<String> listMajor = new ArrayList<String>();
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
								LocalDate birthDate = LocalDate.parse(medicalRecord2.getBirthdate(),
										DateTimeFormatter.ofPattern("MM/dd/yyyy"));
								LocalDate now = LocalDate.now();
								LocalDate date = LocalDate.of(2003, 01, 01);
								long dateBirth = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
//								allInformations.add(firstName);
//								allInformations.add(lastName);
//								allInformations.add(adresse);
//								allInformations.add(phone);
//								allInformations.add(String.valueOf(birthDate));
								if (dateBirth < 18) {
									listMinor.add(birthDate);
									allInformations.add("Minor" + String.valueOf(listMinor.size()));
								} else {
									listMajor.add(String.valueOf(birthDate));
									allInformations.add("Major" + String.valueOf(listMajor.size()));
								}
							}
						}
					}
				}
			}
		}
		return allInformations;
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
	public PersonByStationsAndAddressDto findPersonsByStations(List<Integer> stations) {
		List<FireStation> firestations = dataSource.getFirestations();
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		PersonNamePhoneDto personDto = new PersonNamePhoneDto();
		List<PersonNamePhoneDto> personDtoList = new ArrayList<PersonNamePhoneDto>();
		MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
		List<MedicalRecordDto> medicalRecordDtoList = new ArrayList<MedicalRecordDto>();
		PersonByStationsAndAddressDto personStationDto = new PersonByStationsAndAddressDto();
		List<String> addressList = new ArrayList<String>();
		for (FireStation firestation : firestations) {
			for (Person person : persons) {
				for (MedicalRecord medicalRecord : medicalrecords) {
					if (firestation.getAddress().equals(person.getAddress())) {
						firestation.setPerson(person);
						if (person.getFirstName().equals(medicalRecord.getFirstName())
								&& person.getLastName().equals(medicalRecord.getLastName())) {
							person.setMedicalRecord(medicalRecord);
							for (Integer station : stations) {
								if (firestation.getStation().equals(station)) {
//									if (firestation.getAddress().equals(person.getAddress())) {
										String address = firestation.getAddress();
										addressList.add(address);
										for (String address2 : addressList) {
											if (person.getAddress().equals(address2)) {
												Person person2 = firestation.getPerson();
												MedicalRecord medicalRecord2 = person.getMedicalRecord();
												String firstName = person2.getFirstName();
												String lastName = person2.getLastName();
												String phone = person2.getPhone();
												List<String> allergies = medicalRecord2.getAllergies();
												List<String> medications = medicalRecord2.getMedications();
												LocalDate birthDate = LocalDate.parse(medicalRecord2.getBirthdate(),
														DateTimeFormatter.ofPattern("MM/dd/yyyy"));
												LocalDate now = LocalDate.now();
												long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
												personDto = new PersonNamePhoneDto(firstName, lastName, phone);
												personDtoList.add(personDto);
												medicalRecordDto = new MedicalRecordDto(medications, allergies, age);
												medicalRecordDtoList.add(medicalRecordDto);
												personStationDto = new PersonByStationsAndAddressDto(address,
														personDtoList, medicalRecordDtoList);
//											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return personStationDto;
	}
}