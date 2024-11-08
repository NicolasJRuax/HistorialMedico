package DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.myproyect.HistorialMedico.medicalHistory.MedicalRecord;

import java.util.List;

@Dao
public interface MedicalRecordDao {
    @Insert
    void insertRecord(MedicalRecord record);

    @Update
    void updateRecord(MedicalRecord record);

    @Delete
    void deleteRecord(MedicalRecord record);

    @Query("SELECT * FROM MedicalRecord")
    List<MedicalRecord> getAllRecords();

    @Query("SELECT * FROM MedicalRecord WHERE userId = :userId LIMIT 1")
    MedicalRecord getRecordByUserId(int userId);

    @Query("SELECT * FROM MedicalRecord WHERE id = :id")
    MedicalRecord getRecordById(int id);
}
