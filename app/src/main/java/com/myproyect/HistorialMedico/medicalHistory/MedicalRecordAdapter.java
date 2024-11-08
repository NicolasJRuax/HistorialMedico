package com.myproyect.HistorialMedico.medicalHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.myproyect.HistorialMedico.R;

import java.util.List;

public class MedicalRecordAdapter extends RecyclerView.Adapter<MedicalRecordAdapter.ViewHolder> {

    private List<MedicalRecord> medicalRecords;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MedicalRecord record);
    }

    public MedicalRecordAdapter(List<MedicalRecord> medicalRecords, OnItemClickListener listener) {
        this.medicalRecords = medicalRecords;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textPatientName;
        public TextView textCondition;

        public ViewHolder(View itemView) {
            super(itemView);
            textPatientName = itemView.findViewById(R.id.textPatientName);
            textCondition = itemView.findViewById(R.id.textCondition);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(medicalRecords.get(position));
                }
            });
        }
    }

    @Override
    public MedicalRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicalRecordAdapter.ViewHolder holder, int position) {
        MedicalRecord record = medicalRecords.get(position);
        holder.textPatientName.setText(record.getPatientName());
        holder.textCondition.setText(record.getCondition());
    }

    @Override
    public int getItemCount() {
        return medicalRecords.size();
    }
}
