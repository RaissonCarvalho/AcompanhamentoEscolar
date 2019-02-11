package com.acompanhamentoescolar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.model.Disciplina;
import com.acompanhamentoescolar.model.Nota;
import com.acompanhamentoescolar.persistence.App;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class FormularioNotaActivity extends AppCompatActivity {

    private EditText edValorNota;
    private Button btnSalvarNota;

    private Box<Disciplina> disciplinaBox;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        BoxStore boxStore = ((App) getApplication()).getBoxStore();
        disciplinaBox = boxStore.boxFor(Disciplina.class);

        setupViews();

        long disciplinaId = getIntent().getLongExtra("disciplinaId", -1);

        disciplina = disciplinaBox.get(disciplinaId);

        btnSalvarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarNota();
            }
        });
    }


    private void setupViews(){

        edValorNota = findViewById(R.id.ed_valor_nota);
        btnSalvarNota = findViewById(R.id.btn_salvar_nota);

    }

    private void salvarNota(){
        try {

            double valorNota = Double.parseDouble(edValorNota.getText().toString());

            if (valorNota <0 || valorNota > 10){
                Toast.makeText(getApplicationContext(), "Digite um valor entre 0 e 10", Toast.LENGTH_LONG).show();
            }else{
                Nota nota = new Nota(valorNota, "0");
                disciplina.addNota(nota);
                disciplinaBox.put(disciplina);

                Toast.makeText(getApplicationContext(), "Nova nota salva", Toast.LENGTH_SHORT).show();
                finish();
            }

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Digite a nota a ser adicionada", Toast.LENGTH_SHORT).show();
        }


    }
}
