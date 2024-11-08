package com.myproyect.HistorialMedico.medicalHistory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MedicalRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId; // ID del usuario asociado

    public String patientName;
    public String condition;
    public String treatment;
    public String notes;

    public MedicalRecord(int userId, String patientName, String condition, String treatment, String notes) {
        this.userId = userId;
        this.patientName = patientName;
        this.condition = condition;
        this.treatment = treatment;
        this.notes = notes;
    }

    // Getters y setters
    public String getPatientName() {
        return patientName;
    }

    public String getCondition() {
        return condition;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getNotes() {
        return notes;
    }
}
