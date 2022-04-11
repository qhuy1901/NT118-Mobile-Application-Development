package com.example.a19520113_lab2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a19520113_lab2.R;
import com.example.a19520113_lab2.model.Employee;

import java.util.List;

public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.EmployeeViewHolder>{
    //Dữ liệu hiện thị là danh sách employee
    private final List<Employee> employees;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public EmployeeRecyclerViewAdapter(List<Employee> employees, Context mContext) {
        this.employees = employees;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_employee_for_recyclerview, null, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.tvFullName.setText(employee.getName());
        // If this is a manager -> show icon manager. Otherwise, show Staff in tvPosition
        if (employee.isManager())
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.tvPosition.setVisibility(View.GONE);
        }
        else
        {
            holder.ivManager.setVisibility(View.VISIBLE);
            holder.ivManager.setVisibility(View.GONE);
            holder.tvPosition.setVisibility(View.VISIBLE);
            holder.tvPosition.setText( mContext.getString(R.string.staff));
        }

        // Show different color backgrounds for 2 continuous employees
        if (position%2==0)
        {
            holder.llParent.setBackgroundResource(R.color.white);
        }
        else{
            holder.llParent.setBackgroundResource(R.color.light_blue);
        }
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) holder.tvFullName.getLayoutParams();
        params.width = 1200;
        holder.tvFullName.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        if(employees != null)
            return employees.size();
        return 0;
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llParent;
        private TextView tvFullName;
        private TextView tvPosition;
        private ImageView ivManager;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.item_employee_for_recyclerview_tv_fullname);
            tvPosition = (TextView) itemView.findViewById(R.id.item_employee_for_recyclerview_tv_position);
            ivManager = (ImageView) itemView.findViewById(R.id.item_employee_for_recyclerview_iv_manager);
            llParent = (LinearLayout) itemView.findViewById(R.id.item_employee_for_recyclerview_ll_parent);
        }
    }
}
