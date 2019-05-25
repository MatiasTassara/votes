package com.example.SimulacroParcial.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Votant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String dni;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "votant")
    private List<Vote> votes;

    public Votant(){

    }

    public Votant(String  dni, List<Vote> votes) {
        this.dni = dni;
        this.votes = votes;
    }

    public  String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
    public void addVote(Vote vote){
        this.votes.add(vote);
    }
}
