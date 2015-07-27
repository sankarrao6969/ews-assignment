package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    List<Doctor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);

    List<Doctor> findByFirstNameContainingAndLastNameContainingAllIgnoreCase(String firstName, String lastName);

}