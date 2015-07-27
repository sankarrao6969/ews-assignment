package lk.ac.cmb.ucsc.mcs.ewa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Doctor implements Serializable {

    private static final long serialVersionUID = -4349052212589556727L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String hospital;
    private String email;
    private String contactNo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_speciality", joinColumns = {
            @JoinColumn(name = "doctor_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "speciality_id", referencedColumnName = "id") })
    private List<Speciality> specialities;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Doctor))
            return false;
        Doctor other = (Doctor) obj;
        if (getUsername() == null) {
            if (other.getUsername() != null)
                return false;
        } else if (!getUsername().equals(other.getUsername()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Doctor [getId()=%s, getFirstName()=%s, getLastName()=%s, getUsername()=%s]", getId(),
                getFirstName(), getLastName(), getUsername());
    }

}