package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;
import lk.ac.cmb.ucsc.mcs.ewa.repository.PatientRepository;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class NewSystemService {

    @Autowired
    private PatientRepository patientRepository;

    @GET
    @Path("/patients/firstname/{name}")
    public List<Patient> findPatientsByFirstName(@PathParam("name") String firstName) {
        return patientRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @GET
    @Path("/patients/lastname/{name}")
    public List<Patient> findPatientsByLastName(@PathParam("name") String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @GET
    @Path("/patients/name/{firstName}/{lastName}")
    public List<Patient> findPatientsByFirstNameAndLastName(@PathParam("firstName") String firstName,
            @PathParam("lastName") String lastName) {
        return patientRepository.findByFirstNameContainingAndLastNameContainingAllIgnoreCase(firstName, lastName);
    }

}