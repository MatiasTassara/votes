package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Candidate;
import com.example.SimulacroParcial.service.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;


import java.time.temporal.ChronoUnit;
import java.util.List;



@RestController
@RequestMapping("/Candidate")
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("")
    public void add(@RequestBody Candidate c){
        candidateRepository.save(c);
    }

    @GetMapping("")
    public List<Candidate> getAll(){
        return candidateRepository.findAll();
    }
    @PutMapping("")
    public void modify(@RequestBody Candidate c){
        candidateRepository.save(c);
    }

    @GetMapping("/{id}")
    public Candidate get(@PathVariable("id") Integer id){
        Candidate can = candidateRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Candidato no encontrado"));
        return can;
    }




    @DeleteMapping("{/id}")
    public void deleteCandidate(@PathVariable String id){
        candidateRepository.deleteById(Integer.valueOf(id));
    }


}
