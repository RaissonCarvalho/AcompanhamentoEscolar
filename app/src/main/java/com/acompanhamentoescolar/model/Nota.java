package com.acompanhamentoescolar.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Nota {

    @Id
    private long id;
    private double valorNota;
    private String data;

    public Nota(){

    }

    public Nota(double valorNota, String data) {
        this.valorNota = valorNota;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
