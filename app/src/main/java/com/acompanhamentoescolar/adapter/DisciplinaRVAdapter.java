package com.acompanhamentoescolar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.model.Disciplina;

import io.objectbox.Box;;

public class DisciplinaRVAdapter extends RecyclerView.Adapter<DisciplinaRVAdapter.DisciplinaViewHolder> {

    private Box<Disciplina> disciplinaBox;
    private Context context;

    public DisciplinaRVAdapter(Box<Disciplina> disciplinaBox, Context context) {
        this.disciplinaBox = disciplinaBox;
        this.context = context;
    }

    @NonNull
    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_disciplina, viewGroup, false);

        return new DisciplinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder disciplinaViewHolder, final int posicao) {

        Disciplina disciplina = disciplinaBox.getAll().get(posicao);

        disciplinaViewHolder.txtNome.setText(disciplina.getNome());

        disciplinaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Clicou no: "+posicao, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return (int) disciplinaBox.count();
    }

    class DisciplinaViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txt_nome_disciplina);
        }
    }
}
