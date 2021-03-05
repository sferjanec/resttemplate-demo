package com.crispyecho.resttemplatedemo.service;

import com.crispyecho.resttemplatedemo.model.Person;

public interface PersonService {

    Person saveUpdatePerson(Person person);
    Person findPersonById(Integer id);

}
