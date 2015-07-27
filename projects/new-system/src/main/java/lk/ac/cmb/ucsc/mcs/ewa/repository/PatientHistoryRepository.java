package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistory;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistoryKey;

public interface PatientHistoryRepository extends CrudRepository<PatientHistory, PatientHistoryKey> {

    List<PatientHistory> findByPatient(Patient patient);

}