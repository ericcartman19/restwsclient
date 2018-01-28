package com.bharath.restwsclient;

import javax.print.attribute.standard.Media;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bharath.restwsclient.model.Patient;

public class PatientWSClient {

	private static final String PATIENTS = "/patients";
	private static final String PATIENT_SERVICE_URL = "http://localhost:8080/restws/services/patientservice";

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(PATIENT_SERVICE_URL).path(PATIENTS).path("/{id}").resolveTemplate("id", 123);
		Builder request = target.request();

		// obtenemos objeto response de manera generica
		// Response response = request.get();
		// System.out.println(response.getStatus());

		// tambien podemos espeficar a cxf que clase de objeto o entity que esperamos
		// como respuesta
		Patient patient = request.get(Patient.class);
		System.out.println(patient.getId());
		System.out.println(patient.getName());

		// cuando hacemos update (a traves de PUT) basta con que pasemos el entity como
		// parametro
		// a continuacion CXF se encargar'a de deserializar el xml/json para procesarlo
		// patient.setName("Martin Luther KING");
		// WebTarget puttarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS);
		// Response putResponse = puttarget.request().put(Entity.entity(patient,
		// MediaType.APPLICATION_XML));
		// putResponse.close(); // como siempre es una buena idea cerrar los recuersos
		// que hemos terminado de utilizar
		// System.out.println("Status = " + putResponse.getStatus());

		// crear un nuevo Patien (POST)
		// Patient nuevoPatient = new Patient();
		// nuevoPatient.setName("Wipi ti Bapiti BU");
		//
		// WebTarget postTarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS);
		// Patient createtPatient =
		// postTarget.request().post(Entity.entity(nuevoPatient,
		// MediaType.APPLICATION_XML), Patient.class);
		// System.out.println("createtPatient.id = " + createtPatient.getId());

		// es una buena practica cerrar los recursos, incluso cuando Apche CXF se
		// encargar'a de limpiar eventualmente
		WebTarget deleteTarget = client.target(PATIENT_SERVICE_URL).path(PATIENTS).path("/{id}").resolveTemplate("id",
				124);
		Response deleteResponse = deleteTarget.request().delete();
		System.out.println(deleteResponse.getStatus());

		client.close();

	}

}
