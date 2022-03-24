package com.vladmikh.projects.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.vladmikh.projects.employees.adapters.EmployeeAdapter;
import com.vladmikh.projects.employees.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewEmployees);
        EmployeeAdapter adapter = new EmployeeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Employee empl1 = new Employee();
        Employee empl2 = new Employee();
        empl1.setName("Ivan");
        empl2.setName("Piter");
        empl1.setSurname("Ivanov");
        empl2.setSurname("Petrov");
        List<Employee> employees = new ArrayList<>();
        employees.add(empl1);
        employees.add(empl2);
        adapter.setEmployees(employees);
    }
}