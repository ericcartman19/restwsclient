package com.bharath.restwsclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.bharath.restwsclient.model.Patient;

public class PatientWSClient {
	
	private static final String PATIENT_SERVICE_URL = "http://localhost:8080/restws/services/patientservice";

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}");
		target.resolveTemplate("id", 123)
		Builder request = target.request();
		
		// obtenemos objeto response de manera generica
		// Response response = request.get();
		// System.out.println(response.getStatus());
		
		// tambien podemos espeficar a cxf que clase de objeto o entity que esperamos como respuesta
		Patient patient = request.get(Patient.class);
		System.out.println(patient.getId());
		System.out.println(patient.getName());
		
		
	}

}
