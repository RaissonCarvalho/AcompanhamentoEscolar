package com.acompanhamentoescolar.model;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class Disciplina {

    @Id
    private long id;
    private String nome;
    private String situacao;
    private ToMany<Nota> notas;
    private double mediaAprovativa;

    public Disciplina(){

    }

    public Disciplina(String nome, double mediaAprovativa) {
        this.nome = nome;
        this.mediaAprovativa = mediaAprovativa;
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

    public String getSituacao() {
        return situacao;
    }

    public ToMany<Nota> getNotas() {
        return notas;
    }

    public void setNotas(ToMany<Nota> notas) {
        this.notas = notas;
    }

    public void addNota(Double valor, String data){

        this.notas.add(new Nota(valor, data));
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getMediaAprovativa() {
        return mediaAprovativa;
    }

    public void setMediaAprovativa(double mediaAprovativa) {
        this.mediaAprovativa = mediaAprovativa;
    }

    public double calculaMedia(){
        double media = 0;

        List<Nota> notas = this.notas;
        try {

            for (int i = 0; i < notas.size(); i++){

                media += notas.get(i).getValorNota();

            }

            return media/(notas.size());

        }catch (IndexOutOfBoundsException e){
            return media;
        }
    }

    public String verificaSituacao(){
        String situacao;

        if (calculaMedia() >= this.mediaAprovativa){
            return situacao = "Situação: Aprovado";
        }else{
            return situacao = "Situação: Reprovado";
        }

    }
}