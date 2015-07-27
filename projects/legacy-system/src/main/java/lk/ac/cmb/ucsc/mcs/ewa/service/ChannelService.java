package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import lk.ac.cmb.ucsc.mcs.ewa.entity.ChannelRecord;
import lk.ac.cmb.ucsc.mcs.ewa.exception.ChannelRecordExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.DoctorNotFoundException;

@WebService
public interface ChannelService {

    /**
     * Creates a channel record in the system
     * 
     * @param doctorId id of the doctor for whom the channel is made
     * @param patientName name of the patient
     * @param date date as a string in the format yyyy-MM-dd
     * @param time time as a string in the format hhmm. Ex: 13:15 stands for 1.15pm
     * @return Saved Channel Record
     * @throws ChannelRecordExistsException if there is an existing channel record
     * @throws DoctorNotFoundException if there is no doctor for the given id
     */
    ChannelRecord createChannelRecord(@WebParam(name = "doctorId") long doctorId,
            @WebParam(name = "patientName") String patientName, @WebParam(name = "date") String date,
            @WebParam(name = "time") String time) throws ChannelRecordExistsException, DoctorNotFoundException;

    /**
     * Find booked channel records of a particular doctor for a given date.
     * 
     * @param doctorId Id of doctor
     * @param date Date in string format. Ex: 2015-07-25
     * @return Channel Records
     * @throws DoctorNotFoundException if there is no doctor for the given id
     */
    List<ChannelRecord> findChannelRecordsOfDoctorOnDate(@WebParam(name = "doctorId") long doctorId,
            @WebParam(name = "date") String date) throws DoctorNotFoundException;

}
