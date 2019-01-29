package com.acompanhamentoescolar.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.activity.CadastroDisciplinaActivity;
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
    public void onBindViewHolder(@NonNull final DisciplinaViewHolder disciplinaViewHolder, final int posicao) {

        final Disciplina disciplina = disciplinaBox.getAll().get(posicao);

        disciplinaViewHolder.txtNome.setText(disciplina.getNome());

        disciplinaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final Intent intent = new Intent(context, CadastroDisciplinaActivity.class);
               intent.putExtra("disciplinaId", disciplina.getId());

               context.startActivity(intent);
            }
        });

        disciplinaViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_file, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.op_add_nota_disciplina){
                            Toast.makeText(context, "Falta fazer", Toast.LENGTH_SHORT).show();
                        }

                        if (menuItem.getItemId() == R.id.op_excluir_disciplina){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);

                            builder.setTitle("Aviso ");
                            builder.setMessage("Deseja mesmo remover essa disciplina?");
                            builder.setNegativeButton("Não", null);
                            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removerDisciplina(posicao, disciplinaBox);
                                }
                            });

                            builder.show();

                        }

                        return false;
                    }
                });

                popupMenu.show();
                return false;
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

    private void removerDisciplina(int posicao, Box<Disciplina> disciplinaBox){

        disciplinaBox.remove(disciplinaBox.getAll().get(posicao));
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, (int) disciplinaBox.count());

    }
}
