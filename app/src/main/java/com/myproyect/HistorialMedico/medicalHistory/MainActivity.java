package com.myproyect.HistorialMedico.medicalHistory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;
import com.myproyect.HistorialMedico.medicalHistory.Security.LoginActivity;

import com.myproyect.HistorialMedico.medicalHistory.DB.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crear usuarios de ejemplo en un hilo de fondo
        new Thread(() -> {
            DatabaseHelper.createUser(this, "usuario1", "pass1", "user");
            DatabaseHelper.createUser(this, "doctor1", "pass2", "doctor");
        }).start();

        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }
}
