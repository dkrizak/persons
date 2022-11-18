package com.persons.persons.controller;

import com.persons.persons.model.Employee;
import com.persons.persons.model.Person;
import com.persons.persons.service.EmployeeService;
import com.persons.persons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PersonService personService;

    @PostMapping("/employees/{personId}")
    public ResponseEntity<Employee> createEmployee(@PathVariable(value = "personId") Long personId,
                                                  @RequestBody Employee employee){
        Person person = personService.getPersonById(personId);

        if (person == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setPerson(person);

        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping({"/employees/{id}", "/persons/{id}/employee"})
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id){
        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){

        Employee employee1 = employeeService.getEmployeeById(id);

        if(employee1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee1.setEmployeeNumber(employee.getEmployeeNumber());
        employee1.setHireDate(employee.getHireDate());
        employee1.setActive(employee.isActive());

        return new ResponseEntity<>(employeeService.saveEmployee(employee1), HttpStatus.OK);

    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id){
        try{
            //delete detail
            employeeService.deleteEmployeeById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persons/{personId}/employee")
    public ResponseEntity deleteEmployeeOfPerson(@PathVariable("personId") long personId){


        if(!personService.existsPeronById(personId)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteByPersonId(personId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
