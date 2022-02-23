package com.safety.safetynetalerts.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<MedicalRecord> findAllMedicalrecords() {
		return dataSource.getMedicalrecords();
	}

	@Override
	public boolean deleteMedicalrecordByFirstAndLastName(String firstName, String lastName) {
		List<MedicalRecord> medicalrecords = findAllMedicalrecords();
		boolean isRemoved = medicalrecords.removeIf(medicalrecord -> medicalrecord.getFirstName().equals(firstName)
				&& medicalrecord.getLastName().equals(lastName));
		return isRemoved;
	}

	@Override
	public MedicalRecord saveMedicalrecord(MedicalRecord medicalRecord) {
		List<MedicalRecord> medicalrecords = findAllMedicalrecords();
		medicalrecords.add(medicalRecord);
		return medicalRecord;
	}

	@Override
	public MedicalRecord updateMedicalrecord(MedicalRecord medicalRecord) {
		List<MedicalRecord> medicalrecords = findAllMedicalrecords();
		int index = medicalrecords.indexOf(medicalRecord);
		medicalrecords.set(index, medicalRecord);
		return medicalRecord;
	}
}
