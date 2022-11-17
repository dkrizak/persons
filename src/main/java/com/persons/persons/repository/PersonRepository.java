package com.persons.persons.repository;

import com.persons.persons.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCountry(String country); //returns all tutorials with published value as input
    //List<Person> findByPersonContaining(String name);
}
