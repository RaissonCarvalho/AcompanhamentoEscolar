package com.acompanhamentoescolar.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Disciplina {

    @Id
    private long id;
    private String nome;
    private double media;
    private String situacao;

    public Disciplina(){

    }

    public Disciplina(String nome, double media, String situacao) {
        this.nome = nome;
        this.media = media;
        this.situacao = situacao;
    }

    public Disciplina(String nome) {
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

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}