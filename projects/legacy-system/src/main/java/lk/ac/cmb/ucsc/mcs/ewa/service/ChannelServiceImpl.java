package lk.ac.cmb.ucsc.mcs.ewa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import lk.ac.cmb.ucsc.mcs.ewa.entity.ChannelRecord;
import lk.ac.cmb.ucsc.mcs.ewa.entity.ChannelState;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;
import lk.ac.cmb.ucsc.mcs.ewa.exception.ChannelRecordExistsException;
import lk.ac.cmb.ucsc.mcs.ewa.exception.DoctorNotFoundException;
import lk.ac.cmb.ucsc.mcs.ewa.repository.ChannelRecordRepository;
import lk.ac.cmb.ucsc.mcs.ewa.repository.DoctorRepository;

@WebService(endpointInterface = "lk.ac.cmb.ucsc.mcs.ewa.service.ChannelService")
public class ChannelServiceImpl implements ChannelService {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ChannelRecordRepository channelRecordRepository;

    private Doctor findDoctor(long doctorId) throws DoctorNotFoundException {
        // find doctor record
        Doctor doctor = doctorRepository.findOne(doctorId);

        if (doctor == null) {
            throw new DoctorNotFoundException(String.format("A Doctor with ID %d is not available", doctorId));
        }
        return doctor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lk.ac.cmb.ucsc.mcs.ewa.service.ChannelService#createChannelRecord(long, java.lang.String, java.lang.String,
     * java.lang.String)
     */
    public ChannelRecord createChannelRecord(long doctorId, String patientName, String date, String time)
            throws ChannelRecordExistsException, DoctorNotFoundException {

        Doctor doctor = findDoctor(doctorId);

        ChannelRecord channelRecord = new ChannelRecord();
        channelRecord.setDoctor(doctor);
        channelRecord.setPatientName(patientName);

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date channelDateTime = null;
        try {
            channelDateTime = dateFormat.parse(date + " " + time);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid Channel Date and Time");
        }

        channelRecord.setChannelDateTime(channelDateTime);
        channelRecord.setChannelState(ChannelState.UNCHECKED);

        List<ChannelRecord> existingRecords = channelRecordRepository.findByDoctorAndChannelDateTime(doctor,
                channelDateTime);
        if (existingRecords != null && !existingRecords.isEmpty()) {
            throw new ChannelRecordExistsException();
        }

        return channelRecordRepository.save(channelRecord);
    }

    /*
     * (non-Javadoc)
     * 
     * @see lk.ac.cmb.ucsc.mcs.ewa.service.ChannelService#findChannelRecordsOfDoctorOnDate(long, java.lang.String)
     */
    public List<ChannelRecord> findChannelRecordsOfDoctorOnDate(long doctorId, String date)
            throws DoctorNotFoundException {

        Doctor doctor = findDoctor(doctorId);

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

        Date startDate;
        Date endDate;

        try {
            // Set the start date to 12am of the day being queried
            startDate = dateFormat.parse(date + " " + "00:00");
            // Set the end date to 11.59pm of the day being queried
            endDate = dateFormat.parse(date + " " + "23:59");
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid Channel Date");
        }

        return channelRecordRepository.findByDoctorAndChannelDateTimeBetween(doctor, startDate, endDate);
    }

}
