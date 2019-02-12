package app;

import model.Aluno;
import model.Disciplina;
import model.Escola;
import model.Nota;

public class AppTeste {

    public static void main(String[] args) {

        Escola escola = new Escola("IFPI");
        Escola escola1 = new Escola("IDB");
        Escola escola2 = new Escola("CEV");

        Aluno aluno = new Aluno(1, "Raisson", "2º Periodo");
        Aluno aluno1 = new Aluno(2, "Fernando", "3º Periodo");
        Aluno aluno2 = new Aluno(3, "João", "4º Periodo");
        Aluno aluno3 = new Aluno(4, "Maria", "5º Periodo");

        escola.addNovoAluno(aluno);
        escola.addNovoAluno(aluno1);
        escola1.addNovoAluno(aluno2);
        escola2.addNovoAluno(aluno3);

        Disciplina poo = new Disciplina("POO", 7.0);
        Disciplina bd = new Disciplina("BD", 8.0);
        Disciplina ihc = new Disciplina("IHC", 9.0);
        Disciplina mat = new Disciplina("MAT", 6.0);
        Disciplina ing = new Disciplina("ING", 4.0);
        Disciplina redes = new Disciplina("REDES", 8.0);

        aluno.addDisciplina(poo);
        aluno1.addDisciplina(bd);
        aluno1.addDisciplina(ihc);
        aluno2.addDisciplina(mat);
        aluno3.addDisciplina(redes);
        aluno3.addDisciplina(ing);

        Nota nota = new Nota(8.0);
        Nota nota1 = new Nota(7.0);
        Nota nota2 = new Nota(3.0);
        Nota nota3 = new Nota(5.0);
        Nota nota4 = new Nota(4.0);

        poo.addNota(nota);
        poo.addNota(nota1);
        bd.addNota(nota1);
        bd.addNota(nota2);
        bd.addNota(nota3);
        ihc.addNota(nota4);

        System.out.println("---------- ESCOLAS ----------");

        //Listando Escolas com seus respectivos Alunos
        System.out.println(""+escola.getNome() + ": \n" + listarNomesDeAlunos(escola));
        System.out.println(""+escola1.getNome() + ": \n" + listarNomesDeAlunos(escola1));
        System.out.println(""+escola2.getNome() + ": \n" + listarNomesDeAlunos(escola2));

        System.out.println("---------- ALUNOS ----------");

        //Listando Alunos com suas respectivas Disciplinas
        System.out.println(""+aluno.getNome()+ ": \n" +listarDisciplinasNomes(aluno) + "---");
        System.out.println(""+aluno1.getNome()+ ": \n" +listarDisciplinasNomes(aluno1)+ "---");
        System.out.println(""+aluno2.getNome()+ ": \n" +listarDisciplinasNomes(aluno2)+ "---");
        System.out.println(""+aluno3.getNome()+ ": \n" +listarDisciplinasNomes(aluno3));

        System.out.println("---------- NOTAS ----------");
        
        //Listando Disciplinas com suas respectivas Notas
        System.out.println(""+poo.getNome()+ ": \n" +listarNotasDeDisciplinas(poo));
        System.out.println(""+bd.getNome()+ ": \n" +listarNotasDeDisciplinas(bd));
        System.out.println(""+ihc.getNome()+ ": \n" +listarNotasDeDisciplinas(ihc));

    }

    //Lista Nomes disciplinas de um Aluno específico
    private static String listarDisciplinasNomes(Aluno aluno){
        String nomesDisciplinas = "";
        try {

            for (int i = 0; i < aluno.getDisciplinas().size(); i++){

                nomesDisciplinas += aluno.getDisciplinas().get(i).getNome() + "\n";
            }

            return nomesDisciplinas;
        }catch (IndexOutOfBoundsException e){

            return nomesDisciplinas;

        }
    }

    //Lista Nomes de Alunos de Escolas específicas
    private static String listarNomesDeAlunos(Escola escola){
        String nomesAlunos = "";
        try {

            for (int i = 0; i < escola.getAlunos().size(); i++){

                nomesAlunos += escola.getAlunos().get(i).getNome() + "\n";
            }

            return nomesAlunos;
        }catch (IndexOutOfBoundsException e){

            return nomesAlunos;

        }
    }

    private static String listarNotasDeDisciplinas(Disciplina disciplina){
        String valorNota = "";
        try {

            for (int i = 0; i < disciplina.getNotas().size(); i++){

                valorNota += disciplina.getNotas().get(i).getValorNota() + "\n";
            }

            return valorNota;
        }catch (IndexOutOfBoundsException e){

            return valorNota;

        }
    }

}
