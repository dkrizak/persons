package com.persons.persons.service;

import com.persons.persons.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

    void deleteByPersonId(long personId);

    List<Employee> getAllEmployees();
}
