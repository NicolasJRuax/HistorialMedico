package com.myproyect.HistorialMedico.medicalHistory;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MedicalRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String recordDetails;

    public MedicalRecord(String recordDetails) {
        this.recordDetails = recordDetails;
    }
}