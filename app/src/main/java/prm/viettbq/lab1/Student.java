package prm.viettbq.lab1;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "students")
public class Student implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String fullName;
    private String birthday;
    private int averageScore;

    public Student(String id, String fullName, String birthday, int averageScore) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.averageScore = averageScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }
}
