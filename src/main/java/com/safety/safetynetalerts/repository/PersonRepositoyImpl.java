package com.safety.safetynetalerts.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.model.PersonDto;
import com.safety.safetynetalerts.model.PersonNameDto;
import com.safety.safetynetalerts.model.PersonNameEmailDto;
import com.safety.safetynetalerts.model.PersonNamePhoneStationDto;

@Repository
public class PersonRepositoyImpl implements PersonRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Person> findAllPersons() {
		return dataSource.getPersons();
	}

	@Override
	public boolean deletePersonByFirstAndLastName(String firstName, String lastName) {
		List<Person> persons = findAllPersons();
		boolean isRemoved = persons
				.removeIf(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName));
		return isRemoved;
	}

	@Override
	public Person updatePersonByFirstAndLastName(Person person) {
		List<Person> persons = findAllPersons();
		int index = persons.indexOf(person);
		persons.set(index, person);
		return person;

	}

	@Override
	public Person savePerson(Person person) {
		List<Person> persons = findAllPersons();
		persons.add(person);
		return person;
	}

	@Override
	public PersonByAddressDto findPersonsByAddress(String address) {
		List<Person> persons = findAllPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		List<FireStation> firestations = dataSource.getFirestations();
		PersonNamePhoneStationDto personDto = new PersonNamePhoneStationDto();
		List<PersonNamePhoneStationDto> personDtolist = new ArrayList<PersonNamePhoneStationDto>();
		PersonByAddressDto personByAddressDto = new PersonByAddressDto();
		int station = 0;
		for (Person person : persons) {
			for (FireStation firestation : firestations) {
				if (person.getAddress().equals(firestation.getAddress())) {
					person.setFirestation(firestation);
					if (person.getAddress().equals(address)) {
						station = firestation.getStation();
					}
				}
			}
			for (MedicalRecord medicalrecord : medicalrecords) {
				if (medicalrecord.getLastName().equals(person.getLastName())
						&& medicalrecord.getFirstName().equals(person.getFirstName())) {
					person.setMedicalRecord(medicalrecord);
					if (person.getAddress().equals(address)) {
						String firstName = person.getFirstName();
						String lastName = person.getLastName();
						String phone = person.getPhone();
						MedicalRecord medicalRecord = person.getMedicalRecord();
						List<String> allergies = medicalRecord.getAllergies();
						List<String> medications = medicalRecord.getMedications();
						LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(),
								DateTimeFormatter.ofPattern("MM/dd/yyyy"));
						LocalDate now = LocalDate.now();
						long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
						personDto = new PersonNamePhoneStationDto(firstName, lastName, phone, medications, allergies,
								age);
						personDtolist.add(personDto);
						personByAddressDto = new PersonByAddressDto(personDtolist, station);
					}
				}
			}
		}
		return personByAddressDto;
	}

	@Override
	public List<String> getEmailByCity(String city) {
		List<Person> persons = findAllPersons();
		List<String> emailList = new ArrayList<String>();
		for (Person person : persons) {
			if (person.getCity().equals(city)) {
				String adrressEmail = person.getEmail();
				emailList.add(adrressEmail);
			}
		}
		return emailList;
	}

	@Override
	public ChildrenByAddressDto findChildrenByAdress(String address) {
		List<Person> persons = findAllPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		ChildrenByAddressDto childrenByAdrress = new ChildrenByAddressDto();
		List<PersonNameDto> personNameList = new ArrayList<PersonNameDto>();
		List<PersonDto> personNameList2 = new ArrayList<PersonDto>();
		for (Person person : persons) {
			for (MedicalRecord medicalrecord : medicalrecords) {
				if (medicalrecord.getLastName().equals(person.getLastName())
						&& medicalrecord.getFirstName().equals(person.getFirstName())) {
					person.setMedicalRecord(medicalrecord);
					if (person.getAddress().equals(address)) {
						MedicalRecord medicalRecord = person.getMedicalRecord();
						LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(),
								DateTimeFormatter.ofPattern("MM/dd/yyyy"));
						LocalDate now = LocalDate.now();
						long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
						List<Person> persons2 = new ArrayList<Person>();
						PersonDto personDto2 = new PersonDto();
						persons2.add(person);
						if (age <= 18) {
							String firstName = person.getFirstName();
							String lastName = person.getLastName();
							PersonNameDto personDto = new PersonNameDto(firstName, lastName, age);
							personNameList.add(personDto);
						} else {
							String firstName2 = person.getFirstName();
							String lastName2 = person.getLastName();
							personDto2 = new PersonDto(firstName2, lastName2);
							personNameList2.add(personDto2);
						}
					}
				}
			}
		}
		childrenByAdrress = new ChildrenByAddressDto(personNameList, personNameList2);
		return childrenByAdrress;
	}

	@Override
	public PersonByFirstEtLastNameDto findPersonsByFirstAndLastName(String firstName, String lastName) {
		List<Person> persons = findAllPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		List<PersonNameEmailDto> personDtolist = new ArrayList<PersonNameEmailDto>();
		PersonByFirstEtLastNameDto personByNameDto = new PersonByFirstEtLastNameDto();
		for (Person person : persons) {
			for (MedicalRecord medicalrecord : medicalrecords) {
				if (medicalrecord.getLastName().equals(person.getLastName())
						&& medicalrecord.getFirstName().equals(person.getFirstName())) {
					person.setMedicalRecord(medicalrecord);
					if (person.getLastName().equals(lastName)) {
						String firstname = person.getFirstName();
						String lastname = person.getLastName();
						String address = person.getAddress();
						String email = person.getEmail();
						MedicalRecord medicalRecord = person.getMedicalRecord();
						List<String> allergies = medicalRecord.getAllergies();
						List<String> medications = medicalRecord.getMedications();
						LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(),
								DateTimeFormatter.ofPattern("MM/dd/yyyy"));
						LocalDate now = LocalDate.now();
						long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
						PersonNameEmailDto personNameEmailDto = new PersonNameEmailDto(firstname, lastname, email,
								address, medications, allergies, age);
						personDtolist.add(personNameEmailDto);
						personByNameDto = new PersonByFirstEtLastNameDto(personDtolist);
					}
				}
			}
		}
		return personByNameDto;
	}
}
