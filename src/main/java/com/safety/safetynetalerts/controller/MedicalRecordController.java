package com.safety.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.service.MedicalRecordService;

@CrossOrigin
@RestController
@RequestMapping(path="/medicalrecords")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalrecordService;
	
	@RequestMapping(value = "/medicalrecords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE
			)
	public List<MedicalRecord> findMedicalRecords() {
		return medicalrecordService.getAllMedicalRecords();
	}
}
