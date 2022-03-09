package com.safety.safetynetalerts.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonNamePhoneDto;
import com.safety.safetynetalerts.service.FirestationService;

@CrossOrigin
@RestController
public class FirestationController implements HealthIndicator{

	@Autowired
	private FirestationService firestationService;

	private static Logger logger = LoggerFactory.getLogger(PersonController.class);

	@RequestMapping(value = "/allfirestations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FireStation> findFirestations() {
		return firestationService.getAllFirestations();
	}

	@RequestMapping(value = "/firestation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FireStation> addStation(@RequestBody FireStation firestation) {
		FireStation firestationSaved = firestationService.saveFirestation(firestation);
		logger.info("FIRESTATION CREATED");
		return new ResponseEntity<>(firestationSaved, HttpStatus.CREATED);
	}

	@PutMapping(value = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<FireStation> updateFirestation(
			@Validated @RequestBody FireStation newFirestation) throws IOException {
		FireStation firestationUpdated = firestationService.updateFirestationByAddress(newFirestation);
		logger.info("FIRESTATION UPDATED");
		return new ResponseEntity<>(firestationUpdated, HttpStatus.OK);

	}

	@DeleteMapping(value = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteFirestationByAddress(@RequestParam String address) throws IOException {
		boolean isRemoved = firestationService.deleteFirestationByAddress(address);
		if (!isRemoved) {
			logger.info("FIRESTATION NOT DELETED");
		}
		logger.info("FIRESTATION DELETED");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/firestation/station", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> deleteFirestationByStation(@RequestParam Integer station) throws IOException {
		boolean isRemoved = firestationService.deleteFirestationByStation(station);
		if (!isRemoved) {
			logger.info("FIRESTATION NOT DELETED");
		}
		logger.info("FIRESTATION DELETED");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/phoneAlert")
	@ResponseBody
	public ResponseEntity<List<String>> findPhoneNumberByStationNumber(@RequestParam Integer firestation) {
		List<String> phonesList = firestationService.getPhoneNumbersByStationNumber(firestation);
		return new ResponseEntity<>(phonesList, HttpStatus.OK);

	}

	@GetMapping(value = "flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, List<PersonNamePhoneDto>>> findPersonsByAListOfStations(
			@RequestParam List<Integer> stations) {
		Map<String, List<PersonNamePhoneDto>> personsByAddress = firestationService
				.getPersonsByStationAndAddress(stations);
		return new ResponseEntity<>(personsByAddress, HttpStatus.OK);
	}

	@GetMapping(value = "firestation", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PersonByStationDto> getPersonsByStation(@RequestParam Integer stationNumber) {
		PersonByStationDto personsByStation = firestationService.getPersonsByStation(stationNumber);
		return new ResponseEntity<>(personsByStation, HttpStatus.OK);
	}
	@Override
	public Health health() {
		List<FireStation> firestations = firestationService.getAllFirestations();
		if (firestations.isEmpty()) {
			return Health.down().build();
		}
		return Health.up().build();
	}
}
