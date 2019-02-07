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

    public double getMediaAprovativa() {
        return mediaAprovativa;
    }

    public void setMediaAprovativa(double mediaAprovativa) {
        this.mediaAprovativa = mediaAprovativa;
    }

    public void addNota(Nota nota){
        this.notas.add(nota);
    }

    public double calculaMedia(){
        double media = 0;

        List<Nota> notas = this.notas;
        try {

            for (int i = 0; i < notas.size(); i++){

                media += this.notas.get(i).getValorNota();

            }

            return media/(this.notas.size());

        }catch (IndexOutOfBoundsException e){
            return media;
        }
    }

    public int calculaQtdNotas(){
        return this.notas.size();
    }

    public String verificaValorNotas(){
        String valorNotas = "";

        try {

            if (calculaQtdNotas() <= 0){
                return valorNotas = "Nenhuma Nota adicionada";
            }else{

                for (int i = 0; i < this.notas.size(); i++){

                    valorNotas += "(" +String.valueOf(this.notas.get(i).getValorNota()) + ") ";

                }
            }

            return valorNotas;

        }catch (IndexOutOfBoundsException e){
            return valorNotas;
        }

    }

    public String verificaSituacao(){
        String situacao;

        if (calculaMedia() >= this.mediaAprovativa){
            situacao = "Situação: Aprovado";
            return situacao;
        }else{
            situacao = "Situação: Reprovado";
            return situacao;
        }

    }

    public String verificaResultadoDetelhes(){

        if (calculaQtdNotas() <= 0){
            return "Nenhuma Nota adicionada";
        }else{

            if (verificaSituacao().equals("Situação: Aprovado")){
                return "Você está indo bem. Continue assim!";
            }else{
                return "Você não está bem nessa disciplina. Estude mais!";
            }
        }

    }
}