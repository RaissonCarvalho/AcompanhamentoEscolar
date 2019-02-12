package com.acompanhamentoescolar.model;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Escola {

    @Id
    private long id;
    private String nome;
    private ToMany<Aluno> alunos;

    public Escola(){

    }

    public Escola(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ToMany<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ToMany<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addNovoAluno(Aluno aluno){
        this.alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno){
        this.alunos.remove(aluno);
    }
}
