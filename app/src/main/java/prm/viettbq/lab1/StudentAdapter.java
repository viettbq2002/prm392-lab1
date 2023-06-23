package prm.viettbq.lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import prm.viettbq.lab1.my_interface.IClickItemStudent;
import prm.viettbq.lab1.my_interface.IClickUpdateStudent;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> mListStudent;
    private IClickItemStudent iClickItemStudent;

    private IClickUpdateStudent iClickUpdateStudent;

    public StudentAdapter(IClickItemStudent iClickItemStudent, IClickUpdateStudent iClickUpdateStudent) {
        this.iClickItemStudent = iClickItemStudent;
        this.iClickUpdateStudent = iClickUpdateStudent;
    }

    public void setData(List<Student> list) {
        this.mListStudent = list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = mListStudent.get(position);
        if (student == null) {
            return;
        }
        holder.tvFullName.setText(student.getFullName());
        holder.tvId.setText(student.getId());
        holder.tvAvgScore.setText(String.valueOf(student.getAverageScore()));
        holder.tvBirthday.setText(student.getBirthday());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    iClickItemStudent.onCLickItemStudent(student);
            }
        });

        holder.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickUpdateStudent.onClickUpdateStudent(student);
            }
        });


    }



    @Override
    public int getItemCount() {
        if (mListStudent != null) {
            return mListStudent.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvId;
        private final TextView tvFullName;
        private final TextView tvBirthday;
        private final TextView tvAvgScore;
        private final LinearLayout itemLayout;

        private final Button updateBtn;



        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvBirthday = itemView.findViewById(R.id.tv_birthday);
            tvAvgScore = itemView.findViewById(R.id.tv_avg_score);
            tvFullName = itemView.findViewById(R.id.tv_fullname);
            itemLayout = itemView.findViewById(R.id.item_layout);
            updateBtn = itemView.findViewById(R.id.update_btn);


        }
    }


}
