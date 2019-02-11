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


import com.acompanhamentoescolar.R;
import com.acompanhamentoescolar.activity.FormularioDisciplinaActivity;
import com.acompanhamentoescolar.activity.FormularioNotaActivity;
import com.acompanhamentoescolar.activity.ListaNotasActivity;
import com.acompanhamentoescolar.model.Disciplina;

import io.objectbox.Box;

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

        final Disciplina disciplina = disciplinaBox.getAll().get(disciplinaViewHolder.getAdapterPosition());

        disciplinaViewHolder.txtNome.setText(disciplina.getNome());
        if (disciplina.getNotas().isEmpty()){
            disciplinaViewHolder.txtMedia.setText("");
            disciplinaViewHolder.txtSituacao.setText("");
        }else{
            String media = String.format("%.1f", disciplina.calculaMedia());
            disciplinaViewHolder.txtMedia.setText("Média: " +media);
            disciplinaViewHolder.txtSituacao.setText(disciplina.verificaSituacao());

        }

        disciplinaViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_notas_file, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){

                            case R.id.op_add_nota_disciplina:{

                                final Intent intent = new Intent(context, FormularioNotaActivity.class);
                                intent.putExtra("disciplinaId", disciplina.getId());

                                context.startActivity(intent);

                                break;
                            }

                            case R.id.op_editar_disciplina:{

                                final Intent intent = new Intent(context, FormularioDisciplinaActivity.class);
                                intent.putExtra("disciplinaId", disciplina.getId());

                                context.startActivity(intent);

                                break;
                            }

                            case R.id.op_excluir_disciplina:{

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                builder.setTitle("Aviso ");
                                builder.setMessage("Deseja mesmo remover essa disciplina?");
                                builder.setNegativeButton("Não", null);
                                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        removerDisciplina(disciplinaViewHolder.getAdapterPosition(), disciplinaBox);
                                    }
                                });

                                builder.show();

                                break;
                            }

                            case R.id.op_info_disciplina:{
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                                //Criando AlertDialog para Listar Detalhes
                                final LayoutInflater inflater = LayoutInflater.from(context);
                                View alertDialogView = inflater.inflate(R.layout.dialog_info, null);

                                //Fazendo binding das Views do AlertDialog
                                setupAlertDialogViews(alertDialogView, disciplina);

                                builder.setView(alertDialogView);
                                builder.setTitle("Detalhes ");
                                builder.setPositiveButton("OK", null);
                                builder.setNeutralButton("Editar Notas", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final Intent intent = new Intent(context, ListaNotasActivity.class);

                                        intent.putExtra("disciplinaId", disciplina.getId());
                                        context.startActivity(intent);
                                    }
                                });

                                builder.show();

                                break;
                            }
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
        TextView txtMedia;
        TextView txtSituacao;

        private DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txt_nome_disciplina);
            txtMedia = itemView.findViewById(R.id.txt_media);
            txtSituacao = itemView.findViewById(R.id.txt_situacao);
        }
    }

    private void removerDisciplina(int posicao, Box<Disciplina> disciplinaBox){

        disciplinaBox.remove(disciplinaBox.getAll().get(posicao));
        notifyItemRemoved(posicao);
        notifyItemRangeChanged(posicao, (int) disciplinaBox.count());

    }

    private void setupAlertDialogViews(View alertDialogView ,Disciplina disciplina){

        //Fazendo biding
        TextView txtNomeDisciplina = alertDialogView.findViewById(R.id.txt_info_nome_disciplina);
        TextView txtQtdNotas = alertDialogView.findViewById(R.id.txt_qntd_notas_disciplina);
        TextView txtMediaAprovativa = alertDialogView.findViewById(R.id.txt_info_media_disciplina);
        TextView txtDetalhesDisciplina = alertDialogView.findViewById(R.id.txt_result_info);

        //Setando txt's do AlertDialog
        txtNomeDisciplina.setText("Nome: " +disciplina.getNome());
        txtQtdNotas.setText("Quantidade de Notas: " +disciplina.calculaQtdNotas() + " " + disciplina.verificaValorNotas());
        txtMediaAprovativa.setText("Média Aprovativa: (" +String.valueOf(disciplina.getMediaAprovativa())+ ")");
        txtDetalhesDisciplina.setText(disciplina.verificaResultadoDetelhes());

    }
}
