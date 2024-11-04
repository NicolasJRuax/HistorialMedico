package com.myproyect.HistorialMedico.medicalHistory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText editUsername = findViewById(R.id.editUsername);
        EditText editPassword = findViewById(R.id.editPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();

            if (AuthenticationManager.authenticate(username, password)) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                editPassword.setError("Authentication failed");
            }
        });
    }
}
