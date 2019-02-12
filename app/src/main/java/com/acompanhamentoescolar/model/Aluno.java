package com.acompanhamentoescolar.model;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Aluno {

    @Id
    private long id;
    private String nome;
    private String serie;
    private ToMany<Disciplina> disciplinas;

    public Aluno(){

    }

    public Aluno(long id, String nome, String serie) {
        this.id = id;
        this.nome = nome;
        this.serie = serie;
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

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public ToMany<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ToMany<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina){
        this.disciplinas.remove(disciplina);
    }
}
