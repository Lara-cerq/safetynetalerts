package com.safety.safetynetalerts.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.repository.MedicalRecordRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

	@InjectMocks
	private MedicalRecordService medicalrecordService;

	@Mock
	private MedicalRecordRepositoryImpl medicalRecordRepository;

	@Test
	public void getAllMedicalrecords() {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		List<MedicalRecord> medicalrecords = new ArrayList<>();
		MedicalRecord medicalrecord = new MedicalRecord("Lara", "Cerqueira", "28/10/1993", medications, allergies);
		medicalrecords.add(medicalrecord);
		Mockito.when(medicalRecordRepository.findAllMedicalrecords()).thenReturn(medicalrecords);
		List<MedicalRecord> medicalrecordResultat = medicalrecordService.getAllMedicalRecords();
		assertEquals(medicalrecords.toString(), medicalrecordResultat.toString());
	}

	@Test
	public void addMedicalRecord() {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		MedicalRecord medicalrecord = new MedicalRecord("Lara", "Cerqueira", "28/10/1993", medications, allergies);
		Mockito.when(medicalRecordRepository.saveMedicalrecord(medicalrecord)).thenReturn(medicalrecord);
		MedicalRecord medicalRecordResultat = medicalrecordService.addMedicalrecord(medicalrecord);
		assertEquals(medicalrecord, medicalRecordResultat);

	}

	@Test
	public void updateMedicalRecord() {
		List<String> medications = new ArrayList<>();
		List<String> allergies = new ArrayList<>();
		MedicalRecord medicalrecord = new MedicalRecord("Lara", "Cerqueira", "28/10/1993", medications, allergies);
		Mockito.when(medicalRecordRepository.updateMedicalrecord(medicalrecord)).thenReturn(medicalrecord);
		MedicalRecord medicalRecordResultat = medicalrecordService.updateMedicalrecord(medicalrecord);
		assertEquals(medicalrecord, medicalRecordResultat);
	}
	
	@Test
	public void deleteMedicalRecord() {
		Mockito.when(medicalRecordRepository.deleteMedicalrecordByFirstAndLastName("Lara","Cerqueira")).thenReturn(true);
		Boolean medicalRecordResultat = medicalrecordService.deleteMedicalrecord("Lara", "Cerqueira");
		assertEquals(true, medicalRecordResultat);
	}
}
