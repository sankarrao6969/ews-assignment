package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    List<Doctor> findByLastName(String lastName);

}