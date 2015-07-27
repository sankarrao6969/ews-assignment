package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.jws.WebService;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;

@WebService
public interface DoctorService {

    List<Doctor> getDoctors();

    List<Speciality> getSpecialities();

}
