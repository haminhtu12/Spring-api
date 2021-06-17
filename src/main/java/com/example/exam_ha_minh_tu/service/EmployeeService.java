package com.example.exam_ha_minh_tu.service;

import com.example.exam_ha_minh_tu.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService <T>{
    Employee createEmployee(Employee employee);
    Iterable<T> findAll();
    Optional<T> findById(Long id);

    void remove(Long id);
    List<Employee> searchByName(String name);
}
