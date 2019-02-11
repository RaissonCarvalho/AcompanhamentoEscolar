package com.acompanhamentoescolar.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.model.Disciplina;
import com.acompanhamentoescolar.model.Nota;

import io.objectbox.Box;

public class NotasRVAdapter extends RecyclerView.Adapter<NotasRVAdapter.NotasViewHolder> {

    private Box<Disciplina> disciplinaBox;
    private Context context;
    private long id;

    public NotasRVAdapter(Box<Disciplina> disciplinaBox, Context context, long id) {
        this.disciplinaBox = disciplinaBox;
        this.context = context;
        this.id = id;
    }

    @NonNull
    @Override
    public NotasRVAdapter.NotasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_nota, viewGroup ,false);

        return new NotasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotasRVAdapter.NotasViewHolder notasViewHolder, final int position) {
        final Disciplina disciplina = disciplinaBox.get(id);

        try {

            notasViewHolder.txtValorNota.setText(String.valueOf(disciplina.getNotas().get(notasViewHolder.getAdapterPosition()).getValorNota()));
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(context, "Nenhuma nota cadastrada", Toast.LENGTH_SHORT).show();
        }

        if (disciplina.getNotas().isEmpty()){
            Toast.makeText(context,"Nenhuma nota cadastrada", Toast.LENGTH_SHORT).show();
        }else{

            notasViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Deseja mesmo remover esta nota");
                    builder.setNegativeButton("Não", null);
                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            disciplina.removerNota(disciplina.getNotas().get(notasViewHolder.getAdapterPosition()));
                            disciplinaBox.put(disciplina);

                            notifyItemRemoved(notasViewHolder.getAdapterPosition());
                            notifyItemRangeChanged(notasViewHolder.getAdapterPosition(), disciplina.getNotas().size());
                        }
                    });

                    builder.show();

                    return false;
                }
            });

            notasViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    final LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.dialog_editar_nota, null);

                    final EditText edNovoValorNota = view.findViewById(R.id.ed_novo_valor_nota);

                    builder.setView(view);
                    builder.setTitle("Editar Nota");
                    builder.setNeutralButton("Cancelar", null);
                    builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            double novoValorNota = Double.parseDouble(edNovoValorNota.getText().toString());

                            disciplina.editarNota(new Nota(novoValorNota), position);
                            disciplinaBox.put(disciplina);

                            notifyItemChanged(notasViewHolder.getAdapterPosition(), disciplina);
                            notifyDataSetChanged();
                            Toast.makeText(context, ""+disciplina.verificaValorNotas(), Toast.LENGTH_SHORT).show();

                        }
                    });

                    builder.show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return disciplinaBox.get(id).getNotas().size();
    }

    class NotasViewHolder extends RecyclerView.ViewHolder{

        TextView txtValorNota;

        private NotasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtValorNota = itemView.findViewById(R.id.txt_valor_nota);
        }
    }
}
