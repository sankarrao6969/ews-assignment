package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Doctor findByUsername(String username);

    List<Doctor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Doctor> findByLastNameContainingIgnoreCase(String lastName);

    List<Doctor> findByFirstNameContainingAndLastNameContainingAllIgnoreCase(String firstName, String lastName);

    @Query("select o from Doctor o inner join o.specialities s where s.id = ?1")
    List<Doctor> findBySpeciality(long specialityId);

}