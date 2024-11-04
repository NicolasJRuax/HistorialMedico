package com.myproyect.HistorialMedico.medicalHistory;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.myproyect.HistorialMedico.R;

import DB.DatabaseHelper;

public class MedicalRecordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        EditText editMedicalInfo = findViewById(R.id.editMedicalInfo);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String medicalInfo = editMedicalInfo.getText().toString();
            DatabaseHelper.saveMedicalInfo(MedicalRecordActivity.this, medicalInfo, null);
        });

    }
}
