package com.crispyecho.resttemplatedemo.service;

import com.crispyecho.resttemplatedemo.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonServiceImpl implements PersonService {


    @Override
    public Person saveUpdatePerson(Person person) {
        return person;
    }

    @Override
    public Person findPersonById(Integer id) {
        return new Person(id, "Steve");
    }
}
