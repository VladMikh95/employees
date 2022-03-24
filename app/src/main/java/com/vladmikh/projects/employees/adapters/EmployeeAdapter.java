package com.vladmikh.projects.employees.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vladmikh.projects.employees.R;
import com.vladmikh.projects.employees.pojo.Employee;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.name.setText(employee.getName());
        holder.surname.setText(employee.getSurname());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView surname;
        private TextView position;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            surname = itemView.findViewById(R.id.textViewSurname);
            position = itemView.findViewById(R.id.textViewPosition);
        }
    }


}
