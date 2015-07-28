package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;

@WebService
public interface DoctorService {

    List<Doctor> getDoctors();

    List<Doctor> findDoctorsByFirstName(@WebParam(name = "firstName") String firstName);

    List<Doctor> findDoctorsByLastName(@WebParam(name = "lastName") String lastName);

    List<Doctor> findDoctorsByFirstNameAndLastName(@WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName);

    List<Doctor> findDoctorsBySpeciality(@WebParam(name = "specialityId") long specialityId);

    List<Speciality> getSpecialities();

    List<Speciality> findSpecialities(@WebParam(name = "speciality") String speciality);

}
