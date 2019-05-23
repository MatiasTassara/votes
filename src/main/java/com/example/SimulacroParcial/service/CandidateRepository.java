package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
}
