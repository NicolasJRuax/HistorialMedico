package com.myproyect.HistorialMedico.medicalHistory.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myproyect.HistorialMedico.medicalHistory.Records.MedicalRecord;
import com.myproyect.HistorialMedico.medicalHistory.Security.EncryptionUtils;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {MedicalRecord.class, User.class}, version = 4)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract MedicalRecordDao medicalRecordDao();
    public abstract UserDao userDao();

    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseHelper.class, "medical_history_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Interfaz para callback de un solo registro
    public interface MedicalRecordCallback {
        void onMedicalRecordLoaded(MedicalRecord record);
    }

    public static void getMedicalRecordById(Context context, int id, MedicalRecordCallback callback) {
        new Thread(() -> {
            MedicalRecord record = getDatabase(context).medicalRecordDao().getRecordById(id);
            if (record != null) {
                // Desencriptar datos
                record.patientName = EncryptionUtils.decrypt(record.patientName);
                record.condition = EncryptionUtils.decrypt(record.condition);
                record.treatment = EncryptionUtils.decrypt(record.treatment);
                record.notes = EncryptionUtils.decrypt(record.notes);
            }
            if (callback != null) {
                callback.onMedicalRecordLoaded(record);
            }
        }).start();
    }


    // Interfaz para callback de múltiples registros
    public interface MedicalRecordsCallback {
        void onMedicalRecordsLoaded(List<MedicalRecord> records);
    }

    // Método asíncrono para obtener un historial médico por userId
    public static void getMedicalRecordByUserId(Context context, int userId, MedicalRecordCallback callback) {
        new Thread(() -> {
            MedicalRecord record = getDatabase(context).medicalRecordDao().getRecordByUserId(userId);
            if (record != null) {
                // Desencriptar datos
                record.patientName = EncryptionUtils.decrypt(record.patientName);
                record.condition = EncryptionUtils.decrypt(record.condition);
                record.treatment = EncryptionUtils.decrypt(record.treatment);
                record.notes = EncryptionUtils.decrypt(record.notes);
            }
            if (callback != null) {
                callback.onMedicalRecordLoaded(record);
            }
        }).start();
    }

    // Método asíncrono para obtener todos los historiales médicos
    public static void getAllMedicalRecords(Context context, MedicalRecordsCallback callback) {
        new Thread(() -> {
            List<MedicalRecord> records = getDatabase(context).medicalRecordDao().getAllRecords();
            if (records != null) {
                List<MedicalRecord> decryptedRecords = new ArrayList<>();
                for (MedicalRecord record : records) {
                    // Desencriptar datos
                    record.patientName = EncryptionUtils.decrypt(record.patientName);
                    record.condition = EncryptionUtils.decrypt(record.condition);
                    record.treatment = EncryptionUtils.decrypt(record.treatment);
                    record.notes = EncryptionUtils.decrypt(record.notes);
                    decryptedRecords.add(record);
                }
                if (callback != null) {
                    callback.onMedicalRecordsLoaded(decryptedRecords);
                }
            } else {
                if (callback != null) {
                    callback.onMedicalRecordsLoaded(new ArrayList<>());
                }
            }
        }).start();
    }

    // Otros métodos existentes, asegurándose de que todos operan en hilos de fondo
    public static void saveMedicalInfo(Context context, MedicalRecord record, Runnable onComplete) {
        new Thread(() -> {
            // Encriptar datos
            record.patientName = EncryptionUtils.encrypt(record.patientName);
            record.condition = EncryptionUtils.encrypt(record.condition);
            record.treatment = EncryptionUtils.encrypt(record.treatment);
            record.notes = EncryptionUtils.encrypt(record.notes);

            getDatabase(context).medicalRecordDao().insertRecord(record);
            if (onComplete != null) {
                onComplete.run();
            }
        }).start();
    }

    public static void updateMedicalInfo(Context context, MedicalRecord record, Runnable onComplete) {
        new Thread(() -> {
            // Encriptar datos
            record.patientName = EncryptionUtils.encrypt(record.patientName);
            record.condition = EncryptionUtils.encrypt(record.condition);
            record.treatment = EncryptionUtils.encrypt(record.treatment);
            record.notes = EncryptionUtils.encrypt(record.notes);

            getDatabase(context).medicalRecordDao().updateRecord(record);
            if (onComplete != null) {
                onComplete.run();
            }
        }).start();
    }

    public static void deleteMedicalInfo(Context context, MedicalRecord record, Runnable onComplete) {
        new Thread(() -> {
            getDatabase(context).medicalRecordDao().deleteRecord(record);
            if (onComplete != null) {
                onComplete.run();
            }
        }).start();
    }

    public static void createUser(Context context, String username, String password, String role) {
        new Thread(() -> {
            User user = new User(username, password, role);
            getDatabase(context).userDao().insertUser(user);
        }).start();
    }
}
