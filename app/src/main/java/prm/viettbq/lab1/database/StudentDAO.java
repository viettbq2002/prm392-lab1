package prm.viettbq.lab1.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import prm.viettbq.lab1.Student;

@Dao
public interface StudentDAO {
    @Insert
    void InsertStudent(Student student);
    @Query("select * from students")
    List<Student> getAll();
    @Query("select * from students where id = :ID")
    List<Student>  checkStudent(String ID);
    @Update
    void UpdateStudent(Student student);

}
