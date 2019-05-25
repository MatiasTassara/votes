package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Candidate;
import com.example.SimulacroParcial.model.Votant;
import com.example.SimulacroParcial.model.Vote;
import com.example.SimulacroParcial.service.CandidateRepository;
import com.example.SimulacroParcial.service.VotantRepository;
import com.example.SimulacroParcial.service.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/Person")
public class VotantController {

    @Autowired
    private VotantRepository votantRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;

    public VotantController(){

    }

    @PostMapping("")
    public void add(@RequestBody Votant p){
        votantRepository.save(p);
    }

    @GetMapping("")
    public List<Votant> getAll(){
        return votantRepository.findAll();
    }


    @GetMapping("/{id}")
    public Votant get(@PathVariable ("id") Integer id){
         Votant per = votantRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Persona no encontrada"));
         return per;
    }

    @PostMapping("/{idP}/vote/{idC}")
    public void vote(@PathVariable("idP") Integer idPerson,@PathVariable("idC") Integer idCandidate){

        Votant per = votantRepository.findById(idPerson)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Persona no encontrada"));
        Candidate can = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Candidato no encontrado"));
        if(!isNull(per) && !isNull(can)) {
            Vote vt = new Vote(LocalDateTime.now(), can, per);

            per.addVote(vt);
            can.addVote(vt);
            voteRepository.save(vt);
        }
        votantRepository.save(per);
        candidateRepository.save(can);

    }


}
