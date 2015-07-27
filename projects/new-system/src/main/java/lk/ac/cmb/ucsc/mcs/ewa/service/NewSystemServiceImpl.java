package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lk.ac.cmb.ucsc.mcs.ewa.entity.Patient;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistory;
import lk.ac.cmb.ucsc.mcs.ewa.entity.PatientHistoryKey;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientHistoryExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.PatientNotFoundException;
import lk.ac.cmb.ucsc.mcs.ewa.repository.PatientHistoryRepository;
import lk.ac.cmb.ucsc.mcs.ewa.repository.PatientRepository;

@Component("newSystemService")
public class NewSystemServiceImpl implements NewSystemService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientHistoryRepository patientHistoryRepository;

    public List<Patient> findPatientsByFirstName(String firstName) {
        return patientRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Patient> findPatientsByLastName(String lastName) {
        return patientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Patient> findPatientsByFirstNameAndLastName(String firstName, String lastName) {
        return patientRepository.findByFirstNameContainingAndLastNameContainingAllIgnoreCase(firstName, lastName);
    }

    @Transactional
    public PatientHistory createPatientHistory(long patientId, long channelId)
            throws PatientHistoryExistsException, PatientNotFoundException {
        PatientHistoryKey key = new PatientHistoryKey();
        key.setPatient(patientId);
        key.setChannelId(channelId);
        PatientHistory existingPatientHistory = patientHistoryRepository.findOne(key);

        if (existingPatientHistory != null) {
            throw new PatientHistoryExistsException(String.format(
                    "Patient History record is available for patient %d and channel ID %d", patientId, channelId));
        }

        Patient patient = patientRepository.findOne(patientId);
        if (patient == null) {
            throw new PatientNotFoundException(String.format("Patient %d not found", patientId));
        }

        PatientHistory patientHistory = new PatientHistory();
        patientHistory.setPatient(patient);
        patientHistory.setChannelId(channelId);

        return patientHistoryRepository.save(patientHistory);
    }

    public List<PatientHistory> findPatientHistory(long patientId) {
        Patient patient = new Patient();
        patient.setId(patientId);
        return patientHistoryRepository.findByPatient(patient);
    }

    public PatientHistory findPatientHistory(long patientId, long channelId) {
        PatientHistoryKey key = new PatientHistoryKey();
        key.setPatient(patientId);
        key.setChannelId(channelId);
        return patientHistoryRepository.findOne(key);
    }

}