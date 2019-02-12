package model;

import java.util.ArrayList;
import java.util.List;

public class Escola {

    private long id;
    private String nome;
    private List<Aluno> alunos = new ArrayList<>();

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

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void addNovoAluno(Aluno aluno){
        this.alunos.add(aluno);
    }
}
