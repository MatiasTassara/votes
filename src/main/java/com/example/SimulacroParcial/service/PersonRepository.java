package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {

}
