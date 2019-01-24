package com.acompanhamentoescolar.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.acompanhamentoescolar.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplinas;
    private FloatingActionButton fabAddDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        fabAddDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), CadastroDisciplinaActivity.class));

            }
        });
    }


    public void setupViews(){

        rvListaDisciplinas = findViewById(R.id.rv_lista_disciplinas);
        fabAddDisciplina = findViewById(R.id.fab_add_disciplina);

    }
}
