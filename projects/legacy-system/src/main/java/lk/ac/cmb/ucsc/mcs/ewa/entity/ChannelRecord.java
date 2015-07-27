package lk.ac.cmb.ucsc.mcs.ewa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ChannelRecord implements Serializable {

    private static final long serialVersionUID = 4879439843121341878L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String patientName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date channelDateTime;

    @Enumerated(EnumType.STRING)
    private ChannelState channelState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public ChannelRecord() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getChannelDateTime() {
        return channelDateTime;
    }

    public void setChannelDateTime(Date channelDateTime) {
        this.channelDateTime = channelDateTime;
    }

    public ChannelState getChannelState() {
        return channelState;
    }

    public void setChannelState(ChannelState channelState) {
        this.channelState = channelState;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChannelDateTime() == null) ? 0 : getChannelDateTime().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ChannelRecord))
            return false;
        ChannelRecord other = (ChannelRecord) obj;
        if (getChannelDateTime() == null) {
            if (other.getChannelDateTime() != null)
                return false;
        } else if (!getChannelDateTime().equals(other.getChannelDateTime()))
            return false;
        if (getPatientName() == null) {
            if (other.getPatientName() != null)
                return false;
        } else if (!getPatientName().equals(other.getPatientName()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format(
                "ChannelRecord [getId()=%s, getPatientName()=%s, getChannelDateTime()=%s, getChannelState()=%s, getDoctor()=%s]",
                getId(), getPatientName(), getChannelDateTime(), getChannelState(), getDoctor());
    }

}