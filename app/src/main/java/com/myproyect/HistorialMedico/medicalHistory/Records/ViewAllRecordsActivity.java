package com.myproyect.HistorialMedico.medicalHistory.Records;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;

import com.myproyect.HistorialMedico.medicalHistory.DB.DatabaseHelper;

public class ViewAllRecordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_records);

        TextView textViewRecords = findViewById(R.id.textViewRecords);

        // Obtener todos los historiales médicos utilizando el método asíncrono con callback
        DatabaseHelper.getAllMedicalRecords(this, records -> {
            if (records != null && !records.isEmpty()) {
                StringBuilder recordsDisplay = new StringBuilder();
                for (MedicalRecord record : records) {
                    recordsDisplay.append("Nombre del Paciente: ").append(record.getPatientName()).append("\n");
                    recordsDisplay.append("Condición: ").append(record.getCondition()).append("\n");
                    recordsDisplay.append("Tratamiento: ").append(record.getTreatment()).append("\n");
                    recordsDisplay.append("Notas: ").append(record.getNotes()).append("\n\n");
                }
                // Actualizar la interfaz de usuario en el hilo principal
                runOnUiThread(() -> textViewRecords.setText(recordsDisplay.toString()));
            } else {
                runOnUiThread(() -> {
                    textViewRecords.setText("No se encontraron registros.");
                    Toast.makeText(ViewAllRecordsActivity.this, "No hay registros para mostrar", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}
