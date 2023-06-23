package prm.viettbq.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import prm.viettbq.lab1.database.StudentDatabase;
import prm.viettbq.lab1.my_interface.IClickItemStudent;
import prm.viettbq.lab1.my_interface.IClickUpdateStudent;

public class MainActivity extends AppCompatActivity implements IClickUpdateStudent, IClickItemStudent {

    private EditText editId;
    private EditText editFullName;
    private EditText editAvgScore;
    private EditText editBirthday;
    private Button addStudentBtn;
    private List<Student> mListStudent;
    private RecyclerView rcvStudent;
    private StudentAdapter studentAdapter;
    public ActivityResultLauncher<Intent> mActivityResulLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        //Truy xuat CSDL va dien lai du lieu trong RecylerView
                        loadData();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mListStudent = new ArrayList<>();
        studentAdapter = new StudentAdapter(this::onCLickItemStudent, this::onClickUpdateStudent);
        loadData();
        studentAdapter.setData(mListStudent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvStudent.setLayoutManager(linearLayoutManager);
        rcvStudent.setAdapter(studentAdapter);
        //divider for recycleView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        rcvStudent.addItemDecoration(dividerItemDecoration);

        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });


    }

    private void addStudent() {
        String studentId = editId.getText().toString();
        String birthday = editBirthday.getText().toString();
        String fullName = editFullName.getText().toString();
        String strrAvgScore = editAvgScore.getText().toString();
        if (TextUtils.isEmpty(studentId) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(fullName) || TextUtils.isEmpty(strrAvgScore)) {
            return;
        }
        int avgScore = Integer.parseInt(strrAvgScore);
        Student student = new Student(studentId, fullName, birthday, avgScore);
        if (isStudentExist(student)) {
            Toast.makeText(this, "Student is already exist", Toast.LENGTH_SHORT).show();
            return;
        }
        StudentDatabase.getInstance(this).studentDAO().InsertStudent(student);
        Toast.makeText(this, "add student successfully", Toast.LENGTH_SHORT).show();
        loadData();

    }

    private void goToDetail(Student student) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", student);
        intent.putExtras(bundle);



    }

    void initView() {
        editId = findViewById(R.id.edit_id);
        editFullName = findViewById(R.id.edit_fullname);
        editBirthday = findViewById(R.id.edit_birthday);
        editAvgScore = findViewById(R.id.edit_avg_score);
        addStudentBtn = findViewById(R.id.add_btn);
        rcvStudent = findViewById(R.id.student_rcv);

    }

    private void loadData() {
        mListStudent = StudentDatabase.getInstance(this).studentDAO().getAll();
        studentAdapter.setData(mListStudent);

    }

    private void clickUpdateStudent(Student student) {
        Intent intent = new Intent(MainActivity.this, UpdateStudent.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", student);
        intent.putExtras(bundle);
        mActivityResulLauncher.launch(intent);


    }


    private boolean isStudentExist(@NonNull Student student) {
        List<Student> list = StudentDatabase.getInstance(this).studentDAO().checkStudent(student.getId());
        return list != null && !list.isEmpty();
    }


    @Override
    public void onCLickItemStudent(Student student) {
        goToDetail(student);
    }

    @Override
    public void onClickUpdateStudent(Student student) {
        clickUpdateStudent(student);
    }


}