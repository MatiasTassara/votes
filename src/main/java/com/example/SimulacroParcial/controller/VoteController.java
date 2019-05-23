package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Vote;
import com.example.SimulacroParcial.service.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static java.time.LocalDate.now;

@RestController
public class VoteController {
    @Autowired
    private VoteRepository voteRepository;

    @Scheduled(cron = "00:06:00")
    public void deleteVotes(){
        long minutes;
        List<Vote> lista = voteRepository.findAll();

        for(Vote v: lista) {
            minutes = ChronoUnit.MINUTES.between(v.getDate(), now());
            if(minutes >= 5){
                voteRepository.deleteById(v.getId());
            }
        }
    }

}
