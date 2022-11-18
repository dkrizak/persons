package com.persons.persons.service;

import com.persons.persons.model.Employee;
import com.persons.persons.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {

        Optional<Employee> employeeData = employeeRepository.findById(id);

        if(employeeData.isPresent()){
            return employeeData.get();
        }

        return null;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteByPersonId(long personId) {
        employeeRepository.deleteByPersonId(personId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
