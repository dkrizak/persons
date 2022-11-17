package com.persons.persons.service;

import com.persons.persons.model.Person;

import java.util.List;

public interface PersonService {

    Person savePerson(Person person);
    List<Person> getAllPersons();
    List<Person> getPersonByCountry(String country);
    Person getPersonById(long id);

    Person updatePersonById(long id, Person person);

    void deletePersonById(long id);

    //void deletePersons();
}
