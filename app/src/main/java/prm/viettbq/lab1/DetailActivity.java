package prm.viettbq.lab1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvStudentId;
    private TextView tvBirthday;
    private TextView tvAvgScore;
    private TextView tvFullName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initUI();
        getStudent();

    }

    void initUI() {
        tvStudentId = findViewById(R.id.detail_id);
        tvAvgScore = findViewById(R.id.detail_avg_score);
        tvFullName = findViewById(R.id.detail_fullname);
        tvBirthday = findViewById(R.id.detail_birthday);
    }
    void getStudent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Student student = (Student) bundle.get("student");

        tvStudentId.setText("studentID: " + student.getId());
        tvAvgScore.setText("Average Score: " + String.valueOf(student.getAverageScore()));
        tvFullName.setText("Full name: " + student.getFullName());
        tvBirthday.setText("Birthday: " + student.getBirthday());

    }
}