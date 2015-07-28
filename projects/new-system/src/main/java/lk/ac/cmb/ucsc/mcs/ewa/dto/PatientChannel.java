package lk.ac.cmb.ucsc.mcs.ewa.dto;

import java.io.Serializable;

public class PatientChannel implements Serializable {

    private static final long serialVersionUID = 3506085693118721169L;

    private long patientId;

    private long channelId;

    public PatientChannel() {
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
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
        result = prime * result + (int) (channelId ^ (channelId >>> 32));
        result = prime * result + (int) (patientId ^ (patientId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PatientChannel))
            return false;
        PatientChannel other = (PatientChannel) obj;
        if (channelId != other.channelId)
            return false;
        if (patientId != other.patientId)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("PatientChannel [patientId=%s, channelId=%s]", patientId, channelId);
    }

}
