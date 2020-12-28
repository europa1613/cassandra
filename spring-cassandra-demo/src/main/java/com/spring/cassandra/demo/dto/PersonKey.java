package com.spring.cassandra.demo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class PersonKey implements Serializable {

  @PrimaryKeyColumn(name = "first_name", type = PrimaryKeyType.PARTITIONED)
  private String firstName;

  @PrimaryKeyColumn(name = "date_of_birth", type = PrimaryKeyType.CLUSTERED, ordinal = 0, ordering = Ordering.DESCENDING)
  private LocalDateTime dateOfBirth;

  @PrimaryKeyColumn(name = "person_id", type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.ASCENDING)
  private UUID id;

  public PersonKey() {
  }

  public PersonKey(String firstName, LocalDateTime dateOfBirth, UUID id) {
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.id = id;
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
}
