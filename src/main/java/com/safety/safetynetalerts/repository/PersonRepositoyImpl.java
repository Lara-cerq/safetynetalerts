package com.safety.safetynetalerts.repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.ChildrenByAddressDto;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.MedicalRecordDto;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByAddressDto;
import com.safety.safetynetalerts.model.PersonByFirstEtLastNameDto;
import com.safety.safetynetalerts.model.PersonDto;
import com.safety.safetynetalerts.model.PersonNameAddressDto;
import com.safety.safetynetalerts.model.PersonNameDto;
import com.safety.safetynetalerts.model.PersonNameEmailDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;

@Repository
public class PersonRepositoyImpl implements PersonRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Person> findAllPersons() {
		return dataSource.getPersons();
	}

	@Override
	public Person deletePersonByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updatePersonByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonByAddressDto findPersonsByAddress(String address) {
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		List<FireStation> firestations = dataSource.getFirestations();
		PersonNamePhoneDto personDto = new PersonNamePhoneDto();
		MedicalRecordDto medicalRecordDto = new MedicalRecordDto();
		List<PersonNamePhoneDto> personDtolist = new ArrayList<PersonNamePhoneDto>();
		List<MedicalRecordDto> medicalRecordDtoList = new ArrayList<MedicalRecordDto>();
		PersonByAddressDto personByAddressDto = new PersonByAddressDto();
		int station = 0;
		for (Person person : persons) {
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
						personDto = new PersonNamePhoneDto(firstName, lastName, phone);
						personDtolist.add(personDto);
						medicalRecordDto = new MedicalRecordDto(medications, allergies, age);
						medicalRecordDtoList.add(medicalRecordDto);
						personByAddressDto = new PersonByAddressDto(personDtolist, medicalRecordDtoList, station);
					}
				}
			}
			for (FireStation firestation : firestations) {
				if (person.getAddress().equals(firestation.getAddress())) {
					person.setFirestation(firestation);
					if (person.getAddress().equals(address)) {
						station = firestation.getStation();
					}
				}
			}
		}
		return personByAddressDto;
	}

	@Override
	public List<String> getEmailByCity(String city) {
		List<Person> persons = dataSource.getPersons();
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
		List<Person> persons = dataSource.getPersons();
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
						String lastName = "";
						String firstName="";
						PersonDto personDto2 = new PersonDto();
						persons2.add(person);
						if (age <= 18) {
							firstName = person.getFirstName();
							lastName = person.getLastName();
							PersonNameDto personDto = new PersonNameDto(firstName, lastName, age);
							personNameList.add(personDto);
						}
						for (Person person2 : persons) {
							if (lastName.equals(person2.getLastName())) {
								String firstName2 = person2.getFirstName();
								personDto2 = new PersonDto(firstName2, lastName);
								personNameList2.add(personDto2);
//								childrenByAdrress = new ChildrenByAddressDto(personNameList, personNameList2);
							}
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
		List<Person> persons = dataSource.getPersons();
		List<MedicalRecord> medicalrecords = dataSource.getMedicalrecords();
		List<PersonNameEmailDto> personDtolist = new ArrayList<PersonNameEmailDto>();
		List<MedicalRecordDto> medicalRecordDtoList = new ArrayList<MedicalRecordDto>();
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
						PersonNameEmailDto personNameEmailDto = new PersonNameEmailDto(firstname, lastname, email,
								address);
						personDtolist.add(personNameEmailDto);
						MedicalRecord medicalRecord = person.getMedicalRecord();
						List<String> allergies = medicalRecord.getAllergies();
						List<String> medications = medicalRecord.getMedications();
						LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(),
								DateTimeFormatter.ofPattern("MM/dd/yyyy"));
						LocalDate now = LocalDate.now();
						long age = java.time.temporal.ChronoUnit.YEARS.between(birthDate, now);
						MedicalRecordDto medicalrecordDto = new MedicalRecordDto(medications, allergies, 0L);
						medicalRecordDtoList.add(medicalrecordDto);
						personByNameDto = new PersonByFirstEtLastNameDto(personDtolist, medicalRecordDtoList);
					}
				}
			}
		}
		return personByNameDto;
	}
}
