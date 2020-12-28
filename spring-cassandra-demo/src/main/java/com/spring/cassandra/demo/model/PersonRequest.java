package com.spring.cassandra.demo.model;

import com.spring.cassandra.demo.dto.PersonKey;
import java.time.LocalDateTime;
import java.util.UUID;

public class PersonRequest {

  private String firstName;

  private LocalDateTime dateOfBirth;

  private UUID id;

  private String lastName;

  private double salary;

  public PersonRequest() {
  }

  public PersonRequest(String firstName, LocalDateTime dateOfBirth, UUID id, String lastName, double salary) {
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.id = id;
    this.lastName = lastName;
    this.salary = salary;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public LocalDateTime getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
}
