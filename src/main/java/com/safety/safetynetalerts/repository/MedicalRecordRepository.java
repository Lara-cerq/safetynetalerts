package com.safety.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;

public interface MedicalRecordRepository {

	public List<MedicalRecord> medicalrecords = new ArrayList<MedicalRecord>();

	public List<MedicalRecord> findAllMedicalrecords();
	
	public List<MedicalRecord> findMedicalrecordsByFirstAndLastName(String firstName, String lastName);

	public Person deleteMedicalrecordByFirstAndLastName(String firstName, String lastName);

	public Person updateMedicalrecordByFirstAndLastName(String firstName, String lastName);

	public Person saveMedicalrecord(MedicalRecord medicalRecord);
	
}
