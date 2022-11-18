package com.persons.persons.repository;

import com.persons.persons.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    void deleteById(long id);

    @Transactional
    void deleteByPersonId(long personId);
}
