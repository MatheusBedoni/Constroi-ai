package com.app.equipe.controiai2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.equipe.controiai2.R;

/**
 * Created by matheus on 27/09/16.
 */
public class ChamadasViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNome;
    public TextView tvTelefone;
    public TextView tvProblema;
    public TextView tvProb;
    public TextView tvData;
    public ImageView bttTelefonar;
    public ImageView bttAvaliacao;
    public ImageView ivFotoUsuario;

    public View mView;
    public ChamadasViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        tvData = (TextView)itemView.findViewById(R.id.data);
        tvNome = (TextView)itemView.findViewById(R.id.nome);
        tvTelefone = (TextView)itemView.findViewById(R.id.txt_phone);
        tvProblema = (TextView) itemView.findViewById(R.id.problema);
        bttTelefonar = (ImageView) itemView.findViewById(R.id.imageView5);
        bttAvaliacao = (ImageView) itemView.findViewById(R.id.imageView6);
        ivFotoUsuario = (ImageView)itemView.findViewById(R.id.ivProfissaoFoto);
        tvProb = (TextView)itemView.findViewById(R.id.txt_problema);
    }
}
