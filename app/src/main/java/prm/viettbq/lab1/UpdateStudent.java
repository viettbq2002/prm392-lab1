package prm.viettbq.lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import prm.viettbq.lab1.database.StudentDatabase;

public class UpdateStudent extends AppCompatActivity {
    private EditText editId;
    private EditText editFullName;
    private EditText editAvgScore;
    private EditText editBirthday;
    private Button saveBtn;

    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        initView();
        getStudent();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

    }

    private void updateStudent() {
        String studentId = editId.getText().toString();
        String birthday = editBirthday.getText().toString();
        String fullName = editFullName.getText().toString();
        String strrAvgScore = editAvgScore.getText().toString();
        if (TextUtils.isEmpty(studentId) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(strrAvgScore)) {
            return;
        }
        int avgScore = Integer.parseInt(strrAvgScore);

        //Update student
        mStudent.setBirthday(birthday);
        mStudent.setFullName(fullName);
        mStudent.setId(studentId);
        mStudent.setAverageScore(avgScore);

        StudentDatabase.getInstance(this).studentDAO().UpdateStudent(mStudent);
        Toast.makeText(this, "Update student successfully", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK , intentResult);
        finish();

    }


    void initView() {
        editId = findViewById(R.id.edit_id);
        editFullName = findViewById(R.id.edit_fullname);
        editBirthday = findViewById(R.id.edit_birthday);
        editAvgScore = findViewById(R.id.edit_avg_score);
        saveBtn = findViewById(R.id.confirm_update_btn);

    }

    void getStudent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mStudent = (Student) bundle.get("student");
        if (mStudent != null) {
            editBirthday.setText(mStudent.getBirthday());
            editId.setText(mStudent.getId());
            editFullName.setText(mStudent.getFullName());
            editAvgScore.setText(String.valueOf(mStudent.getAverageScore()));

        }
    }


}