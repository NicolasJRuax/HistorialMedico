package com.myproyect.HistorialMedico.medicalHistory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MedicalRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String patientName;
    public String condition;
    public String treatment;
    public String notes;

    public MedicalRecord(String patientName, String condition, String treatment, String notes) {
        this.patientName = patientName;
        this.condition = condition;
        this.treatment = treatment;
        this.notes = notes;
    }
}