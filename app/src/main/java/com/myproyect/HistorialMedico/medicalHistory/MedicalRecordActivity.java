package com.myproyect.HistorialMedico.medicalHistory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.myproyect.HistorialMedico.R;
import DB.DatabaseHelper;

public class MedicalRecordActivity extends AppCompatActivity {
    private Button btnUpdate, btnDelete, btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnViewAll = findViewById(R.id.btnViewAll);

        EditText editPatientName = findViewById(R.id.editPatientName);
        EditText editCondition = findViewById(R.id.editCondition);
        EditText editTreatment = findViewById(R.id.editTreatment);
        EditText editNotes = findViewById(R.id.editNotes);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String patientName = editPatientName.getText().toString();
            String condition = editCondition.getText().toString();
            String treatment = editTreatment.getText().toString();
            String notes = editNotes.getText().toString();

            if (patientName.isEmpty() || condition.isEmpty()) {
                Toast.makeText(this, "Patient name and condition are required", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseHelper.saveMedicalInfo(this, new MedicalRecord(patientName, condition, treatment, notes), () -> {
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
                btnViewAll.setEnabled(true);
                runOnUiThread(() -> Toast.makeText(this, "Record saved successfully", Toast.LENGTH_SHORT).show());
            });

            btnViewAll.setOnClickListener(x -> {
                Intent intent = new Intent(MedicalRecordActivity.this, ViewAllRecordsActivity.class);
                startActivity(intent);
            });
        });
    }
}
