package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.repository.DoctorRepository;

@WebService(endpointInterface = "lk.ac.cmb.ucsc.mcs.ewa.service.DoctorService")
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = new ArrayList<Doctor>();
        for (Doctor doctor : doctorRepository.findAll()) {
            doctors.add(doctor);
        }
        return doctors;
    }
}
