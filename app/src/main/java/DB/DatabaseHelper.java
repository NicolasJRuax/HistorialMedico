package DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.myproyect.HistorialMedico.medicalHistory.MedicalRecord;

@Database(entities = {MedicalRecord.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract MedicalRecordDao medicalRecordDao();

    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseHelper.class, "medical_history_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void saveMedicalInfo(Context context, String info, Runnable callback) {
        new Thread(() -> {
            MedicalRecord record = new MedicalRecord(info);
            getDatabase(context).medicalRecordDao().insertRecord(record);
            if (callback != null) {
                callback.run();
            }
        }).start();
    }
}