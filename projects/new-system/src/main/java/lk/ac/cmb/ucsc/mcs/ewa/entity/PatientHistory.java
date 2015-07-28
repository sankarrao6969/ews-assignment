package lk.ac.cmb.ucsc.mcs.ewa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(PatientHistoryKey.class)
public class PatientHistory implements Serializable {

    private static final long serialVersionUID = 2488162242174939651L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Id
    private long channelId;

    private String comments;

    public PatientHistory() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getChannelId() ^ (getChannelId() >>> 32));
        result = prime * result + ((getPatient() == null) ? 0 : getPatient().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PatientHistory))
            return false;
        PatientHistory other = (PatientHistory) obj;
        if (getChannelId() != other.getChannelId())
            return false;
        if (getPatient() == null) {
            if (other.getPatient() != null)
                return false;
        } else if (!getPatient().equals(other.getPatient()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("PatientChannel [getPatient()=%s, getChannelId()=%s]", getPatient(), getChannelId());
    }

}