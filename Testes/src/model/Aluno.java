package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private long id;
    private String nome;
    private String serie;
    private List<Disciplina> disciplinas = new ArrayList<>();

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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public void addDisciplina(Disciplina disciplina){
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina){
        this.disciplinas.remove(disciplina);
    }
}
