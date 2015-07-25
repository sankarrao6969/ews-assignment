package lk.ac.cmb.ucsc.mcs.ewa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Doctor[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

}