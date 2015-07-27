package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistory;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientHistoryExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientNotFoundException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NewSystemService {

    @GET
    @Path("/patients/firstname/{name}")
    List<Patient> findPatientsByFirstName(@PathParam("name") String firstName);

    @GET
    @Path("/patients/lastname/{name}")
    List<Patient> findPatientsByLastName(@PathParam("name") String lastName);

    @GET
    @Path("/patients/name/{firstName}/{lastName}")
    List<Patient> findPatientsByFirstNameAndLastName(@PathParam("firstName") String firstName,
            @PathParam("lastName") String lastName);

    @POST
    @Path("/patienthistory/{patientId}/{channelId}")
    PatientHistory createPatientHistory(@PathParam("patientId") long patientId, @PathParam("channelId") long channelId)
            throws PatientHistoryExistsException, PatientNotFoundException;

    @GET
    @Path("/patienthistory/{patientId}")
    List<PatientHistory> findPatientHistory(@PathParam("patientId") long patientId);

    @GET
    @Path("/patienthistory/{patientId}/{channelId}")
    PatientHistory findPatientHistory(@PathParam("patientId") long patientId, @PathParam("channelId") long channelId);

}