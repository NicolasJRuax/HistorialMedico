package com.myproyect.HistorialMedico.medicalHistory.Records;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;

import com.myproyect.HistorialMedico.medicalHistory.DB.DatabaseHelper;

public class EditMedicalRecordActivity extends AppCompatActivity {

    private EditText editPatientName, editCondition, editTreatment, editNotes;
    private Button btnSave, btnDelete;
    private int recordId = -1;
    private MedicalRecord record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medical_record);

        editPatientName = findViewById(R.id.editPatientName);
        editCondition = findViewById(R.id.editCondition);
        editTreatment = findViewById(R.id.editTreatment);
        editNotes = findViewById(R.id.editNotes);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        recordId = getIntent().getIntExtra("recordId", -1);

        if (recordId != -1) {
            // Editar registro existente
            DatabaseHelper.getMedicalRecordById(this, recordId, rec -> {
                if (rec != null) {
                    record = rec;
                    runOnUiThread(() -> {
                        editPatientName.setText(record.getPatientName());
                        editCondition.setText(record.getCondition());
                        editTreatment.setText(record.getTreatment());
                        editNotes.setText(record.getNotes());
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(EditMedicalRecordActivity.this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                }
            });
        } else {
            // Nuevo registro
            btnDelete.setEnabled(false);
        }

        btnSave.setOnClickListener(v -> {
            String patientName = editPatientName.getText().toString();
            String condition = editCondition.getText().toString();
            String treatment = editTreatment.getText().toString();
            String notes = editNotes.getText().toString();

            if (patientName.isEmpty() || condition.isEmpty()) {
                Toast.makeText(this, "El nombre y la condición son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            if (recordId != -1) {
                // Actualizar registro existente
                record.patientName = patientName;
                record.condition = condition;
                record.treatment = treatment;
                record.notes = notes;
                DatabaseHelper.updateMedicalInfo(this, record, () -> {
                    runOnUiThread(() -> {
                        Toast.makeText(EditMedicalRecordActivity.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                });
            } else {
                // Crear nuevo registro
                MedicalRecord newRecord = new MedicalRecord(0, patientName, condition, treatment, notes);
                DatabaseHelper.saveMedicalInfo(this, newRecord, () -> {
                    runOnUiThread(() -> {
                        Toast.makeText(EditMedicalRecordActivity.this, "Registro guardado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                });
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (record != null) {
                DatabaseHelper.deleteMedicalInfo(this, record, () -> {
                    runOnUiThread(() -> {
                        Toast.makeText(EditMedicalRecordActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                });
            }
        });
    }
}
