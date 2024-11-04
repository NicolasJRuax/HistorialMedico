package DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.myproyect.HistorialMedico.medicalHistory.MedicalRecord;

import java.util.List;

@Dao
public interface MedicalRecordDao {
    @Insert
    void insertRecord(MedicalRecord record);

    @Query("SELECT * FROM MedicalRecord")
    List<MedicalRecord> getAllRecords();
}
