package com.spring.cassandra.demo.springcassandrademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {CassandraAutoConfiguration.class,
		CassandraDataAutoConfiguration.class})
public class SpringCassandraDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCassandraDemoApplication.class, args);
	}

}
