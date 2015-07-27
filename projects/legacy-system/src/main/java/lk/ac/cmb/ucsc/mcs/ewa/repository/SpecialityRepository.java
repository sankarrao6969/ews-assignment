package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {

    List<Speciality> findBySpecialityContainingIgnoreCase(String speciality);

}