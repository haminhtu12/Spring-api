package com.example.exam_ha_minh_tu.service;

import com.example.exam_ha_minh_tu.dto.MapperDto;
import com.example.exam_ha_minh_tu.entity.Employee;
import com.example.exam_ha_minh_tu.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceIpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    MapperDto mapperDto;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Iterable findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return employeeRepository.findById(Math.toIntExact(id));
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByName(name);
    }
}
