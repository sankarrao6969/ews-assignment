package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;
import lk.ac.cmb.ucsc.mcs.ewa.exception.LoginException;

@WebService
public interface DoctorService {

    void login(@WebParam(name = "username") String username, @WebParam(name = "password") String password)
            throws LoginException;

    List<Doctor> getDoctors();

    List<Doctor> findDoctorsByFirstName(@WebParam(name = "firstName") String firstName);

    List<Doctor> findDoctorsByLastName(@WebParam(name = "lastName") String lastName);

    List<Doctor> findDoctorsByFirstNameAndLastName(@WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName);

    List<Doctor> findDoctorsBySpeciality(@WebParam(name = "specialityId") long specialityId);

    List<Speciality> getSpecialities();

    List<Speciality> findSpecialities(@WebParam(name = "speciality") String speciality);

}
