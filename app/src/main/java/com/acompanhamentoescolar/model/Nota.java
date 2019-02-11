package com.acompanhamentoescolar.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Nota {

    @Id
    private long id;
    private double valorNota;

    public Nota(){

    }

    public Nota(double valorNota) {
        this.valorNota = valorNota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

}
