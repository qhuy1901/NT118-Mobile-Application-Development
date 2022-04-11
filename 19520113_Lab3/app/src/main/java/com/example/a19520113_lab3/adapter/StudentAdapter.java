package com.example.a19520113_lab3.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a19520113_lab3.QuanLyContactActivity;
import com.example.a19520113_lab3.R;
import com.example.a19520113_lab3.UpdateStudentActivity;
import com.example.a19520113_lab3.database.DatabaseHandler;
import com.example.a19520113_lab3.database.StudentDatabase;
import com.example.a19520113_lab3.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private final List<Student> list;
    private Context context;

    public StudentAdapter(List<Student> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Student student = list.get(position);
        if(student == null)
            return ;

        holder.tvFullName.setText(student.getFullName());
        holder.tvDOB.setText(student.getDob());
        holder.tvGPA.setText("GPA: " + student.getGpa());
        int gender = student.getGender();
        if(gender == 1) // male
            holder.ivStudentImage.setImageResource(R.drawable.male_student);
        else
            holder.ivStudentImage.setImageResource(R.drawable.female_student);
        holder.tvClassName.setText(student.getClassName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(context, UpdateStudentActivity.class);
                switchActivityIntent.putExtra("studentInfo", student);
                context.startActivity(switchActivityIntent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc muốn xóa sinh viên này?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StudentDatabase studentDatabase = new StudentDatabase(context);
                                studentDatabase.deleteStudent(student);
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        private TextView tvFullName, tvGPA, tvDOB, tvClassName;
        private ImageView ivStudentImage;
        private ConstraintLayout clParent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.item_student_tv_full_name);
            tvGPA = itemView.findViewById(R.id.item_student_tv_gpa);
            tvDOB = itemView.findViewById(R.id.item_student_tv_dob);
            tvClassName = itemView.findViewById(R.id.item_student_tv_class_name);
            ivStudentImage = itemView.findViewById(R.id.item_student_iv_student_image);
            clParent = itemView.findViewById(R.id.item_student_cl_parent);
        }
    }
}
