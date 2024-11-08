package com.myproyect.HistorialMedico.medicalHistory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;

import DB.DatabaseHelper;

public class MedicalRecordActivity extends AppCompatActivity {

    private int userId;
    private MedicalRecord existingRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_record);

        userId = getIntent().getIntExtra("userId", -1);
        if (userId == -1) {
            Toast.makeText(this, "Usuario no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        EditText editPatientName = findViewById(R.id.editPatientName);
        EditText editCondition = findViewById(R.id.editCondition);
        EditText editTreatment = findViewById(R.id.editTreatment);
        EditText editNotes = findViewById(R.id.editNotes);
        Button btnSave = findViewById(R.id.btnSave);

        // Recuperar el registro existente en un hilo de fondo
        DatabaseHelper.getMedicalRecordByUserId(this, userId, record -> {
            existingRecord = record;
            if (existingRecord != null) {
                runOnUiThread(() -> {
                    // Poblar campos
                    editPatientName.setText(existingRecord.getPatientName());
                    editCondition.setText(existingRecord.getCondition());
                    editTreatment.setText(existingRecord.getTreatment());
                    editNotes.setText(existingRecord.getNotes());
                });
            }
        });

        btnSave.setOnClickListener(v -> {
            String patientName = editPatientName.getText().toString();
            String condition = editCondition.getText().toString();
            String treatment = editTreatment.getText().toString();
            String notes = editNotes.getText().toString();

            if (patientName.isEmpty() || condition.isEmpty()) {
                Toast.makeText(this, "El nombre y la condición son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            MedicalRecord record = new MedicalRecord(userId, patientName, condition, treatment, notes);

            if (existingRecord != null) {
                record.id = existingRecord.id;
                DatabaseHelper.updateMedicalInfo(this, record, () -> {
                    runOnUiThread(() -> Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show());
                });
            } else {
                DatabaseHelper.saveMedicalInfo(this, record, () -> {
                    runOnUiThread(() -> Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show());
                });
            }
        });
    }
}
