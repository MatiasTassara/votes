package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.model.Candidate;
import com.example.SimulacroParcial.model.Person;
import com.example.SimulacroParcial.model.Vote;
import com.example.SimulacroParcial.service.CandidateRepository;
import com.example.SimulacroParcial.service.PersonRepository;
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
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private VoteRepository voteRepository;

    public PersonController(){

    }

    @PostMapping("")
    public void add(@RequestBody Person p){
        personRepository.save(p);
    }

    @GetMapping("")
    public List<Person> getAll(){
        return personRepository.findAll();
    }


    //si llega a dar error ver si id es string o integer
    @GetMapping("/{id}")
    public Person get(@PathVariable ("id") Integer id){
         Person per = personRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Persona no encontrada"));
         return per;
    }

    @PostMapping("/{idP}/vote/{idC}")
    public void vote(@PathVariable("idP") Integer idPerson,@PathVariable("idC") Integer idCandidate){

        Person per = personRepository.findById(idPerson)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Persona no encontrada"));
        Candidate can = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Candidato no encontrado"));
        if(!isNull(per) && !isNull(can)) {
            Vote vt = new Vote(LocalDateTime.now(), can, per);

            per.addVote(vt);
            can.addVote(vt);
            voteRepository.save(vt);
        }
        personRepository.save(per);
        candidateRepository.save(can);

    }


}
