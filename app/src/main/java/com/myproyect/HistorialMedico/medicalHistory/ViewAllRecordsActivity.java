package com.myproyect.HistorialMedico.medicalHistory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.myproyect.HistorialMedico.R;
import java.util.List;
import DB.DatabaseHelper;

public class ViewAllRecordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_records);

        TextView textViewRecords = findViewById(R.id.textViewRecords);

        // Fetch all medical records from the database on a background thread
        new FetchRecordsTask(textViewRecords).execute();
    }

    private static class FetchRecordsTask extends AsyncTask<Void, Void, List<MedicalRecord>> {
        private final TextView textViewRecords;

        FetchRecordsTask(TextView textViewRecords) {
            this.textViewRecords = textViewRecords;
        }

        @Override
        protected List<MedicalRecord> doInBackground(Void... voids) {
            try {
                return DatabaseHelper.getAllMedicalRecords(textViewRecords.getContext());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<MedicalRecord> records) {
            if (records != null && !records.isEmpty()) {
                StringBuilder recordsDisplay = new StringBuilder();
                for (MedicalRecord record : records) {
                    recordsDisplay.append("Patient Name: ").append(record.getPatientName()).append("\n");
                    recordsDisplay.append("Condition: ").append(record.getCondition()).append("\n");
                    recordsDisplay.append("Treatment: ").append(record.getTreatment()).append("\n");
                    recordsDisplay.append("Notes: ").append(record.getNotes()).append("\n\n");
                }
                textViewRecords.setText(recordsDisplay.toString());
            } else {
                textViewRecords.setText("No records found.");
                Toast.makeText(textViewRecords.getContext(), "No records to display", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
