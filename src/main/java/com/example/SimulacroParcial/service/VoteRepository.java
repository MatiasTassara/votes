package com.example.SimulacroParcial.service;

import com.example.SimulacroParcial.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Integer> {
}
