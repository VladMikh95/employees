package com.vladmikh.projects.employees.api;

import com.vladmikh.projects.employees.pojo.Employee;
import com.vladmikh.projects.employees.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}
