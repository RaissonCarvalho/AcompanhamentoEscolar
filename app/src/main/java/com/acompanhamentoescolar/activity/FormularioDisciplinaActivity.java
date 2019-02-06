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

public class FormularioDisciplinaActivity extends AppCompatActivity {

    private EditText edNomeDisciplina;
    private EditText edMediaAprovativa;
    private Button btnSalvarNovaDisciplina;

    private Box<Disciplina> disciplinaBox;

    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_disciplina);

        setupViews();

        BoxStore boxStore = ((App)getApplication()).getBoxStore();
        disciplinaBox = boxStore.boxFor(Disciplina.class);

        long id = getIntent().getLongExtra("disciplinaId", -1);

        if (id == -1){
            //Nova disciplina
            disciplina = new Disciplina();
        }else{
            this.setTitle("Editar");
            disciplina = disciplinaBox.get(id);
            edNomeDisciplina.setText(disciplina.getNome());
            edMediaAprovativa.setText(String.valueOf(disciplina.getMediaAprovativa()));


        }

        btnSalvarNovaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salvarDisciplina();

            }
        });
    }

    public void setupViews(){

        edNomeDisciplina = findViewById(R.id.ed_nome_disciplina);
        btnSalvarNovaDisciplina = findViewById(R.id.btn_salvar_nova_disciplina);
        edMediaAprovativa = findViewById(R.id.ed_media_aprovativa);

    }

    public void salvarDisciplina(){

        try {

            String nome = edNomeDisciplina.getText().toString();
            double mediaAprovativa = Double.parseDouble(edMediaAprovativa.getText().toString());

            if (nome.equals("")){
                Toast.makeText(getApplicationContext(), "Digite o nome da disciplina", Toast.LENGTH_SHORT).show();
            }else{

                disciplina.setNome(nome);
                disciplina.setMediaAprovativa(mediaAprovativa);
                disciplinaBox.put(disciplina);

                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
                finish();
            }

        }catch (NumberFormatException e){
        }
        Toast.makeText(getApplicationContext(), "Digite a média da disciplina", Toast.LENGTH_SHORT).show();

    }
}
