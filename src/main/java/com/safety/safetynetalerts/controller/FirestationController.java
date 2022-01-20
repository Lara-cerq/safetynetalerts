package com.safety.safetynetalerts.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.safety.safetynetalerts.model.FireStation;
import com.safety.safetynetalerts.model.Person;
import com.safety.safetynetalerts.model.PersonByStationDto;
import com.safety.safetynetalerts.model.PersonByStationsAndAddressDto;
import com.safety.safetynetalerts.service.FirestationService;

@CrossOrigin
@RestController
@RequestMapping(path = "/firestations")
public class FirestationController {

	@Autowired
	private FirestationService firestationService;

	@RequestMapping(value = "/allFirestations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FireStation> findFirestations() {
		return firestationService.getAllFirestations();
	}

	// http://localhost:8080/firestation?stationNumber=<station_number>
//	@GetMapping("/firestation") 
//	@ResponseBody
//	public ArrayList<String> findPersonsByStationNumber(@RequestParam Integer stationNumber){
//		return firestationService.getPersonsByStationNumber(stationNumber);
//	}

	@GetMapping("/firestation")
	@ResponseBody
	public PersonByStationDto findPersonsByStationNumber(@RequestParam Integer stationNumber) {
		return firestationService.getPersonsByStation(stationNumber);
	}

	// http://localhost:8080/phoneAlert?firestation=<firestation_number>
	@GetMapping("/phoneAlert")
	@ResponseBody
	public List<String> findPhoneNumberByStationNumber(@RequestParam Integer firestation) {
		return firestationService.getPhoneNumbersByStationNumber(firestation);
	}

	//	http://localhost:8080/flood/stations?stations=<a list of station_numbers>
	@GetMapping("/stations")
	@ResponseBody
	public PersonByStationsAndAddressDto findPersonsByAListOfStations(@RequestParam List<Integer> firestations) {
		return firestationService.getPersonsByStationAndAddress(firestations);
	}
}
