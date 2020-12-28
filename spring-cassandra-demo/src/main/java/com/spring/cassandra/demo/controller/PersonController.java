package com.spring.cassandra.demo.controller;

import com.spring.cassandra.demo.dto.Person;
import com.spring.cassandra.demo.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @Autowired
  private PersonRepository repository;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/people")
  public List<Person> people() {
    return repository.findAll();
  }

  @GetMapping(value = "/people", params = "first")
  public List<Person> peopleByFirstName(@RequestParam String first) {
    return repository.findByKeyFirstName(first);
  }

  @GetMapping(value = "/people", params = "last")
  public List<Person> peopleByLastName(@RequestParam String last) {
    return repository.findByLastName(last);
  }

  @GetMapping(value = "/people", params = "first_name_query")
  public List<Person> peopleByFirstNameQuery(@RequestParam(name = "first_name_query") String name) {
    return repository.findAllQuery(name);
  }

}
