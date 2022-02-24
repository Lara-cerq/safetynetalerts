package com.safety.safetynetalerts.repository;

import java.util.List;

import com.safety.safetynetalerts.model.MedicalRecord;

public interface MedicalRecordRepository {

	public List<MedicalRecord> findAllMedicalrecords();

	public boolean deleteMedicalrecordByFirstAndLastName(String firstName, String lastName);

	public MedicalRecord saveMedicalrecord(MedicalRecord medicalRecord);
	
	public MedicalRecord updateMedicalrecord(MedicalRecord medicalRecord);
	
}
