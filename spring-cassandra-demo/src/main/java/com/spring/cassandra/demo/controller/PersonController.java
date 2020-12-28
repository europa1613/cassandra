package com.spring.cassandra.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cassandra.demo.dto.Person;
import com.spring.cassandra.demo.dto.PersonKey;
import com.spring.cassandra.demo.model.PersonRequest;
import com.spring.cassandra.demo.repository.PersonRepository;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

  @Autowired
  private PersonRepository repository;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/people")
  public List<Person> people() throws JsonProcessingException {
    LOGGER.info("===========> PersonRequest: {}",
        new ObjectMapper().writeValueAsString(new PersonRequest()));
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

  @PostMapping("/people")
  public ResponseEntity<HttpStatus> addPerson(@RequestBody PersonRequest personRequest) {
    UUID id = UUID.randomUUID();
    PersonKey key = new PersonKey(personRequest.getFirstName(), personRequest.getDateOfBirth(), id);
    Person person = new Person(key, personRequest.getLastName(), personRequest.getSalary());
    repository.insert(person);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
