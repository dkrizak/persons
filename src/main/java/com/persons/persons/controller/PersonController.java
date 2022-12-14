package com.persons.persons.controller;

import com.persons.persons.model.Employee;
import com.persons.persons.model.Person;
import com.persons.persons.service.EmployeeService;
import com.persons.persons.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/persons")
    public String getPersons(Model model){

        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("newPerson", new Person());

        List<Employee> employees = employeeService.getAllEmployees();

        ArrayList<Long> employeeIdList = new ArrayList<>();

        for (Employee employee : employees){
            employeeIdList.add(employee.getId());
        }

        model.addAttribute("employeeIdList", employeeIdList);

        return "person/showPersons";
    }

    @PostMapping("/persons/savePerson")
    public String savePerson(Person person){

        personService.savePerson(person);

        return "redirect:/persons";
    }

    @PostMapping("/persons/deletePerson")
    public String deletePerson(@RequestParam long id){

        personService.deletePersonById(id);

        return "redirect:/persons";
    }

    @GetMapping("/persons/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){

        model.addAttribute("person", personService.getPersonById(id));

        return "person/updatePerson";
    }
}
