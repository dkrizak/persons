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

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PersonService personService;

    @GetMapping("/employees")
    public String getEmployees(Model model){

        model.addAttribute("employees", employeeService.getAllEmployees());

        return "employee/showEmployees";
    }

    @PostMapping("/employees/saveEmployee")
    public String savePerson(Employee employee,
                             @RequestParam("personId") long id,
                             @RequestParam(value = "activeEmpChk", required = false) boolean active){

        employee.setId(id);
        employee.setActive(active);
        employee.setPerson(personService.getPersonById(id));

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

    @PostMapping("/employees/deleteEmployee")
    public String deleteEmployee(@RequestParam long id){

        employeeService.deleteEmployeeById(id);

        return "redirect:/employees";
    }

    @GetMapping("/employee/updateEmployee/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeService.getEmployeeById(id));

        return "employee/updateEmployee";
    }

    @GetMapping("/employees/addEmployee")
    public String addEmployee(@RequestParam("id") long id, Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("personId", id);

        return "employee/showAddEmployee";
    }

    @GetMapping("/employees/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model){

        model.addAttribute("employee", employeeService.getEmployeeById(id));

        return "employee/updateEmployee";
    }
}
