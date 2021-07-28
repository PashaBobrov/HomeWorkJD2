package iClinic.model;

import iClinic.model.user.Specialisation;
import iClinic.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "TimeTable")
@Table(name = "timetable")
public class TimeTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @Column(name = "datetime")
    private Date datetime;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    @JoinColumn(name = "patient_id")
    private User patient;

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }



    //    public TimeTable(User doctor, Date datetime) {
//        this.doctor = doctor;
//        this.datetime = datetime;
//    }
//
//    public TimeTable() {
//    }
}


