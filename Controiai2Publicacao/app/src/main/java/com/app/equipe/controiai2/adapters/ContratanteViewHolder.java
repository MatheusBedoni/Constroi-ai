package com.app.equipe.controiai2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.equipe.controiai2.R;

/**
 * Created by Matt on 23/04/2017.
 */

public class ContratanteViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNome;
    public TextView tvTelefone;
    public TextView tvProblema;
    public TextView data;
    public ImageView ivFotoUsuario;

    public View mView;
    public ContratanteViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        tvNome = (TextView)itemView.findViewById(R.id.txt_nome);
        tvTelefone = (TextView)itemView.findViewById(R.id.txt_telefone);
        tvProblema = (TextView) itemView.findViewById(R.id.txt_problema);
        data = (TextView)itemView.findViewById(R.id.problema);
        ivFotoUsuario = (ImageView)itemView.findViewById(R.id.ivProfissaoFoto);
    }
}
