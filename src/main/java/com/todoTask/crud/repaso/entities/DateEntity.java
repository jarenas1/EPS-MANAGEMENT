package com.todoTask.crud.repaso.entities;

import com.todoTask.crud.repaso.tools.enums.DateStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dates")
public class DateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private Integer duration = 30;

    private String reason;

    @Enumerated(EnumType.STRING)
    private DateStatus status;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    public DateEntity(LocalDateTime dateTime, DoctorEntity doctor, Integer duration, Long id, String notes, PatientEntity patient, String reason, DateStatus status) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.duration = duration;
        this.id = id;
        this.notes = notes;
        this.patient = patient;
        this.reason = reason;
        this.status = status;
    }

    public DateEntity() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DateStatus getStatus() {
        return status;
    }

    public void setStatus(DateStatus status) {
        this.status = status;
    }
}
