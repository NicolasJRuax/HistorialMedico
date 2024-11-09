package com.myproyect.HistorialMedico.medicalHistory.Security;

import android.content.Context;

import com.myproyect.HistorialMedico.medicalHistory.DB.DatabaseHelper;
import com.myproyect.HistorialMedico.medicalHistory.DB.User;

public class AuthenticationManager {

    public interface AuthenticationCallback {
        void onAuthenticationSuccess(User user);
        void onAuthenticationFailure();
    }

    public static void authenticate(Context context, String username, String password, AuthenticationCallback callback) {
        new Thread(() -> {
            User user = DatabaseHelper.getDatabase(context).userDao().authenticate(username, password);
            if (user != null) {
                callback.onAuthenticationSuccess(user);
            } else {
                callback.onAuthenticationFailure();
            }
        }).start();
    }
}
