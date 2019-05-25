package com.example.SimulacroParcial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @JsonIgnore
    private Candidate candidate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "votant_id", referencedColumnName = "id")
    @JsonIgnore
    private Votant votant;

    public Vote(){

    }
    public Vote(LocalDateTime localDateTime, Candidate candidate, Votant votant) {
        this.date = localDateTime;
        this.candidate = candidate;
        this.votant = votant;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Votant getVotant() {
        return votant;
    }

    public void setVotant(Votant votant) {
        this.votant = votant;
    }

    @Override
    public String toString() {
        return "Id de Voto: " + getId() + "\nid votante: " + getVotant().getId() + "\nid candidato: " + getCandidate().getId();
    }
}
