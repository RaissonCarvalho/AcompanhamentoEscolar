package com.acompanhamentoescolar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.persistence.App;
import com.acompanhamentoescolar.model.Disciplina;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private EditText edNomeDisciplina;
    private Button btnSalvarNovaDisciplina;

    private Box<Disciplina> disciplinaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        setupViews();

        BoxStore store = ((App)getApplication()).getBoxStore();
        disciplinaBox = store.boxFor(Disciplina.class);

        btnSalvarNovaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salvarNovaDisciplina();

            }
        });
    }

    public void setupViews(){

        edNomeDisciplina = findViewById(R.id.ed_nome_disciplina);
        btnSalvarNovaDisciplina = findViewById(R.id.btn_salvar_nova_disciplina);

    }

    public void salvarNovaDisciplina(){

        String nome = edNomeDisciplina.getText().toString();

        Disciplina disciplina = new Disciplina(nome);

        disciplinaBox.put(disciplina);

        Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();

        finish();

    }
}
