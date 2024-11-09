package com.myproyect.HistorialMedico.medicalHistory;

import android.content.Context;

import DB.DatabaseHelper;
import DB.User;

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
