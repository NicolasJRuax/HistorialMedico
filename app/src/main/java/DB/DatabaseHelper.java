package DB;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myproyect.HistorialMedico.medicalHistory.MedicalRecord;

import java.util.List;

@Database(entities = {MedicalRecord.class, User.class}, version = 3)
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
                            .fallbackToDestructiveMigration() // Agrega esta línea
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public static void saveMedicalInfo(Context context, MedicalRecord record, Runnable onComplete) {
        new Thread(() -> {
            getDatabase(context).medicalRecordDao().insertRecord(record);
            if (onComplete != null) {
                onComplete.run();
            }
        }).start();
    }

    public static List<MedicalRecord> getAllMedicalRecords(Context context) {
        return getDatabase(context).medicalRecordDao().getAllRecords();
    }


    public static void createUser(Context context, String username, String password) {
        new Thread(() -> {
            User user = new User(username, password);
            getDatabase(context).userDao().insertUser(user);
        }).start();
    }



}