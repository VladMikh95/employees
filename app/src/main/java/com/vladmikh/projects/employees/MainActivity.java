package com.vladmikh.projects.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vladmikh.projects.employees.adapters.EmployeeAdapter;
import com.vladmikh.projects.employees.api.ApiFactory;
import com.vladmikh.projects.employees.api.ApiService;
import com.vladmikh.projects.employees.pojo.Employee;
import com.vladmikh.projects.employees.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewEmployees);
        EmployeeAdapter adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ApiFactory factory = ApiFactory.getInstance();
        ApiService apiService = factory.getApiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io()) //the method specifies in which thread the actions will be performed (accessing the database, retrieving data from the Internet)
                .observeOn(AndroidSchedulers.mainThread()) //the method specifies in which stream we will receive data
                .subscribe(new Consumer<EmployeeResponse>() { //the method specifies what to do with the received data
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        adapter.setEmployees(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Data acquisition error", Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }
}