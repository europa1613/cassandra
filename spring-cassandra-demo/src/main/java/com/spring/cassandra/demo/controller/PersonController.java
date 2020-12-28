package com.spring.cassandra.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

}
