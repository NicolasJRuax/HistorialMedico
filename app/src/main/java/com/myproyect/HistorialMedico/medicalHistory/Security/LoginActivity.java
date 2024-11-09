package com.myproyect.HistorialMedico.medicalHistory.Security;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.myproyect.HistorialMedico.R;
import com.myproyect.HistorialMedico.medicalHistory.Doc.DoctorActivity;
import com.myproyect.HistorialMedico.medicalHistory.Records.MedicalRecordActivity;

import com.myproyect.HistorialMedico.medicalHistory.DB.DatabaseHelper;
import com.myproyect.HistorialMedico.medicalHistory.DB.User;

public class LoginActivity extends AppCompatActivity {
    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = editUsername.getText().toString();
            String password = editPassword.getText().toString();

            new AuthenticateTask().execute(username, password);
        });
    }

    private class AuthenticateTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... params) {
            String username = params[0];
            String password = params[1];
            return DatabaseHelper.getDatabase(LoginActivity.this).userDao().authenticate(username, password);
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                Intent intent;
                if ("user".equals(user.role)) {
                    intent = new Intent(LoginActivity.this, MedicalRecordActivity.class);
                    intent.putExtra("userId", user.id);
                } else if ("doctor".equals(user.role)) {
                    intent = new Intent(LoginActivity.this, DoctorActivity.class);
                } else {
                    editPassword.setError("Rol desconocido");
                    return;
                }
                startActivity(intent);
            } else {
                editPassword.setError("Autenticación fallida");
            }
        }
    }
}
