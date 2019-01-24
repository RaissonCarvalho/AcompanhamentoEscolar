package com.acompanhamentoescolar.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.adapter.DisciplinaRVAdapter;
import com.acompanhamentoescolar.model.Disciplina;
import com.acompanhamentoescolar.persistence.App;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplinas;
    private FloatingActionButton fabAddDisciplina;

    private Box<Disciplina> disciplinaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);

        fabAddDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CadastroDisciplinaActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarDisciplinas();
    }

    public void setupViews(){

        rvListaDisciplinas = findViewById(R.id.rv_lista_disciplinas);
        fabAddDisciplina = findViewById(R.id.fab_add_disciplina);

    }

    public void carregarDisciplinas(){
        DisciplinaRVAdapter adapter = new DisciplinaRVAdapter(disciplinaBox, this);

        rvListaDisciplinas.setAdapter(adapter);
        rvListaDisciplinas.setLayoutManager(new LinearLayoutManager(this));
        rvListaDisciplinas.setHasFixedSize(true);


    }
}
