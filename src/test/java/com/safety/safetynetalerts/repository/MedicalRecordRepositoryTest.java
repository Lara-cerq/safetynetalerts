package com.safety.safetynetalerts.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.MedicalRecord;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MedicalRecordRepositoryTest {

	@Autowired
	private MedicalRecordRepositoryImpl medicalRepository;

	@Autowired
	private DataSource dataSource;

	@Test
	public void contextLoads() {
		assertNotNull(medicalRepository);
	}

	@Test
	public void getAllMedicalRecords() {
		List<MedicalRecord> medicalRecords = dataSource.getMedicalrecords();
		List<MedicalRecord> medicalrecordsResult = medicalRepository.findAllMedicalrecords();
		assertNotNull(medicalRecords);
		assertNotNull(medicalrecordsResult);
		assertEquals(medicalrecordsResult, medicalRecords);
	}

	@Test
	public void deleteMedicalRecords() {
		boolean isRemoved = medicalRepository.deleteMedicalrecordByFirstAndLastName("John", "Boyd");
		assertEquals(isRemoved, true);
	}

	@Test
	public void saveMedicalRecords() {
		List<MedicalRecord> medicalRecords = dataSource.getMedicalrecords();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		MedicalRecord medicalRecord = new MedicalRecord("Lara", "Cerqueira", "28/10/1993", medications, allergies);
		MedicalRecord medicalRecordResult= medicalRepository.saveMedicalrecord(medicalRecord);
		boolean result= medicalRecords.contains(medicalRecordResult);
		assertEquals(result, true);
	}
	
	@Test
	public void updateMedicalRecord() {
		List<MedicalRecord> medicalRecords = dataSource.getMedicalrecords();
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		MedicalRecord medicalRecord = new MedicalRecord("John", "Boyd", "28/10/1993", medications, allergies);
		MedicalRecord medicalRecordResult= medicalRepository.saveMedicalrecord(medicalRecord);
		boolean result= medicalRecords.contains(medicalRecordResult);
		assertEquals(result, true);
	}
}
