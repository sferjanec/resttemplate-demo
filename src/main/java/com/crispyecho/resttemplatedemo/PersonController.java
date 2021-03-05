package com.crispyecho.resttemplatedemo;

import com.crispyecho.resttemplatedemo.model.Person;
import com.crispyecho.resttemplatedemo.service.PersonService;
import com.crispyecho.resttemplatedemo.service.PersonServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
public class PersonController {

    PersonService personService = new PersonServiceImpl();

    public PersonController() {}

    @PostMapping(value = "/createPerson", consumes = "application/json", produces = "application/json")
    public Person createPerson(@RequestBody Person person) {
        return personService.saveUpdatePerson(person);
    }

    @PostMapping(value = "/updatePerson", consumes = "application/json", produces = "application/json")
    public Person updatePerson(@RequestBody Person person, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/findPerson/" + person.getId()).toUriString());

        return personService.saveUpdatePerson(person);
    }



}
