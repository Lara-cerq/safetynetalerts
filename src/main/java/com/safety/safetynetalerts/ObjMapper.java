package com.safety.safetynetalerts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
@Configuration
public class ObjMapper {

	public static void main(String[] args) throws IOException {

		// read json file data to String
		byte[] jsonData = Files.readAllBytes(Paths.get("data.json"));

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		

		// convert json string to object
		DataSource dataSource = objectMapper.readValue(jsonData, DataSource.class);
		System.out.println(dataSource.toString());
		
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

}
