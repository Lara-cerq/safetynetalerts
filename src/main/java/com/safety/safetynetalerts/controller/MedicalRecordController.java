package com.safety.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.MedicalRecord;
import com.safety.safetynetalerts.service.MedicalRecordService;

@CrossOrigin
@RestController
public class MedicalRecordController implements HealthIndicator {

	@Autowired
	private MedicalRecordService medicalrecordService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@RequestMapping(value = "/medicalrecords", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MedicalRecord> findMedicalRecords() {
		return medicalrecordService.getAllMedicalRecords();
	}

	@RequestMapping(value = "/medicalrecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalrecord) {
		MedicalRecord medicalrecordSaved = medicalrecordService.addMedicalrecord(medicalrecord);
		logger.info("MEDICALRECORD CREATED");
		return new ResponseEntity<>(medicalrecordSaved, HttpStatus.CREATED);
	}

	@PutMapping(value = "/medicalrecord", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<MedicalRecord> updateMedicalrecord(
			@Validated @RequestBody MedicalRecord newMedicalrecord) {
		MedicalRecord medicalrecordUpdated = medicalrecordService.updateMedicalrecord(newMedicalrecord);
		logger.info("MEDICALRECORD UPDATED");
		return new ResponseEntity<>(medicalrecordUpdated, HttpStatus.OK);

	}

	@DeleteMapping(value = "/medicalrecord", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MedicalRecord> deleteMedicalrecord(@RequestParam String firstName,
			@RequestParam String lastName) throws IOException {
		boolean isRemoved = medicalrecordService.deleteMedicalrecord(firstName, lastName);
		if (!isRemoved) {
			logger.info("MEDICALRECORD NOT DELETED");
		}
		logger.info("MEDICALRECORD DELETED");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@Override
	public Health health() {
		List<MedicalRecord> medicalRecords = medicalrecordService.getAllMedicalRecords();
		if (medicalRecords.isEmpty()) {
			return Health.down().build();
		}
		return Health.up().build();
	}
}
