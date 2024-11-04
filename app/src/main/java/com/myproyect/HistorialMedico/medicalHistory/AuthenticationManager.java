package com.myproyect.HistorialMedico.medicalHistory;

public class AuthenticationManager {
    public static boolean authenticate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}