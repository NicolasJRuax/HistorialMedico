package com.myproyect.HistorialMedico.medicalHistory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproyect.HistorialMedico.R;

import java.util.ArrayList;
import java.util.List;

import DB.DatabaseHelper;

public class DoctorActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRecords;
    private MedicalRecordAdapter adapter;
    private List<MedicalRecord> medicalRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        recyclerViewRecords = findViewById(R.id.recyclerViewRecords);
        recyclerViewRecords.setLayoutManager(new LinearLayoutManager(this));

        medicalRecords = new ArrayList<>();
        adapter = new MedicalRecordAdapter(medicalRecords, record -> {
            Intent intent = new Intent(DoctorActivity.this, EditMedicalRecordActivity.class);
            intent.putExtra("recordId", record.id);
            startActivity(intent);
        });
        recyclerViewRecords.setAdapter(adapter);

        Button btnAddRecord = findViewById(R.id.btnAddRecord);
        btnAddRecord.setOnClickListener(v -> {
            Intent intent = new Intent(DoctorActivity.this, EditMedicalRecordActivity.class);
            startActivity(intent);
        });

        fetchAllRecords();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAllRecords();
    }

    private void fetchAllRecords() {
        DatabaseHelper.getAllMedicalRecords(this, records -> {
            runOnUiThread(() -> {
                if (records != null && !records.isEmpty()) {
                    medicalRecords.clear();
                    medicalRecords.addAll(records);
                    adapter.notifyDataSetChanged();
                } else {
                    medicalRecords.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(DoctorActivity.this, "No hay registros para mostrar", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
