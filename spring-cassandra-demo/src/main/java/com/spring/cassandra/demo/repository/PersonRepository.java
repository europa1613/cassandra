package com.spring.cassandra.demo.repository;

import com.spring.cassandra.demo.dto.Person;
import com.spring.cassandra.demo.dto.PersonKey;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CassandraRepository<Person, PersonKey> {

  List<Person> findByKeyFirstName(final String firstName);

  List<Person> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(
      final String firstName, final LocalDateTime dateOfBirth);

  // Don't do this!!
  @Query(allowFiltering = true)
  List<Person> findByLastName(final String lastName);

  @Query("select * from people_by_first_name where first_name=?0")
  List<Person> findAllQuery(final String name);

}
