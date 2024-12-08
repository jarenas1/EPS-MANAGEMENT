package com.todoTask.crud.repaso.dto.request;

import com.todoTask.crud.repaso.entities.DoctorEntity;
import com.todoTask.crud.repaso.entities.PatientEntity;
import com.todoTask.crud.repaso.tools.enums.DateStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
//WO STATUS AND WO DURATION
public class DateWOStatus {
    private LocalDateTime dateTime;

    private String reason;

    private String notes;

    private PatientEntity patient;

    private DoctorEntity doctor;

    public DateWOStatus(LocalDateTime dateTime, DoctorEntity doctor, String notes, PatientEntity patient, String reason) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.notes = notes;
        this.patient = patient;
        this.reason = reason;
    }

    public DateWOStatus() {
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
}
