package com.safety.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.safety.safetynetalerts.DataSource;
import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.model.Person;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<MedicalRecord> findAllMedicalrecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalRecord> findMedicalrecordsByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person deleteMedicalrecordByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updateMedicalrecordByFirstAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person saveMedicalrecord(MedicalRecord medicalRecord) {
		// TODO Auto-generated method stub
		return null;
	}
}
