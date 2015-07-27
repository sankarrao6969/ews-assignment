package lk.ac.cmb.ucsc.mcs.ewa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lk.ac.cmb.ucsc.mcs.ewa.entity.ChannelRecord;
import lk.ac.cmb.ucsc.mcs.ewa.entity.Doctor;

public interface ChannelRecordRepository extends CrudRepository<ChannelRecord, Long> {

    List<ChannelRecord> findByDoctorAndChannelDateTime(Doctor doctor, Date channelDateTime);

    List<ChannelRecord> findByDoctorAndChannelDateTimeBetween(Doctor doctor, Date startDate, Date endDate);

}