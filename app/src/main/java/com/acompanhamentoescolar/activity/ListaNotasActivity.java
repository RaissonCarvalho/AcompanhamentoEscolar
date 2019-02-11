package com.acompanhamentoescolar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.adapter.NotasRVAdapter;
import com.acompanhamentoescolar.model.Disciplina;
import com.acompanhamentoescolar.persistence.App;

import io.objectbox.Box;

public class ListaNotasActivity extends AppCompatActivity {

    private RecyclerView rvListaNotas;
    private Box<Disciplina> disciplinaBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        setupViews();

        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarNotas();
    }

    private void setupViews(){

        rvListaNotas = findViewById(R.id.rv_lista_notas);
    }

    private void carregarNotas(){
        NotasRVAdapter adapter = new NotasRVAdapter(disciplinaBox, this, getIntent().getLongExtra("disciplinaId", -1));

        rvListaNotas.setAdapter(adapter);
        rvListaNotas.setLayoutManager(new LinearLayoutManager(this));
        rvListaNotas.setHasFixedSize(true);

    }
}
