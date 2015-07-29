package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    Patient findByUsername(String username);

    List<Patient> findByFirstNameContainingIgnoreCase(String firstName);

    List<Patient> findByLastNameContainingIgnoreCase(String lastName);

    List<Patient> findByFirstNameContainingAndLastNameContainingAllIgnoreCase(String firstName, String lastName);

}