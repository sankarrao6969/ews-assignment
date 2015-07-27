package lk.ac.cmb.ucsc.mcs.ewa.entity;

import java.io.Serializable;

public class PatientHistoryKey implements Serializable {

    private static final long serialVersionUID = -1987565283989777977L;

    private long patient;

    private long channelId;

    public PatientHistoryKey() {
    }

    public long getPatient() {
        return patient;
    }

    public void setPatient(long patient) {
        this.patient = patient;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (getChannelId() ^ (getChannelId() >>> 32));
        result = prime * result + (int) (getPatient() ^ (getPatient() >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PatientHistoryKey))
            return false;
        PatientHistoryKey other = (PatientHistoryKey) obj;
        if (getChannelId() != other.getChannelId())
            return false;
        if (getPatient() != other.getPatient())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("PatientHistoryKey [getPatient()=%s, getChannelId()=%s]", getPatient(), getChannelId());
    }

}