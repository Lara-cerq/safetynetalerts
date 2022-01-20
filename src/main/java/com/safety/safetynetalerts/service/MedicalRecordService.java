package com.safety.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.repository.MedicalRecordRepositoryImpl;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordRepositoryImpl medicalRecordRepository;

	public MedicalRecordService(MedicalRecordRepositoryImpl medicalRecordRepository) {
		super();
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public void setMedicalRecordRepository(MedicalRecordRepositoryImpl medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecordRepository.findAllMedicalrecords();
	}

	public List<MedicalRecord> getMedicalRecord(String firstName, String lastName) {
		return medicalRecordRepository.findMedicalrecordsByFirstAndLastName(firstName, lastName);
	}
}
