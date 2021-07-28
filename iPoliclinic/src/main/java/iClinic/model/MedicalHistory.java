package iClinic.model;

import iClinic.model.user.Role;
import iClinic.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "MedicalHistory")
@Table(name = "medical_history")
public class MedicalHistory implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne (fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne (fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(name = "date")
    private Date date;

    @Column(name = "info")
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
