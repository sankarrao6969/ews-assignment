package lk.ac.cmb.ucsc.mcs.ewa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speciality implements Serializable {

    private static final long serialVersionUID = 6591085769845775737L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String speciality;

    public Speciality() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSpeciality() == null) ? 0 : getSpeciality().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Speciality))
            return false;
        Speciality other = (Speciality) obj;
        if (getSpeciality() == null) {
            if (other.getSpeciality() != null)
                return false;
        } else if (!getSpeciality().equals(other.getSpeciality()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Speciality [getId()=%s, getSpeciality()=%s]", getId(), getSpeciality());
    }

}