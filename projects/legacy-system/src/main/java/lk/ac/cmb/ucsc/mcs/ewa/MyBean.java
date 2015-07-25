package lk.ac.cmb.ucsc.mcs.ewa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.repository.DoctorRepository;

@Component
public class MyBean implements CommandLineRunner {

    @Autowired
    private DoctorRepository repository;

    public void run(String... strings) throws Exception {
        repository.deleteAll();
        // save doctors
        repository.save(new Doctor("John", "Paul"));
        repository.save(new Doctor("Jack", "William"));
        repository.save(new Doctor("Ralph", "Bruce"));
    }
}