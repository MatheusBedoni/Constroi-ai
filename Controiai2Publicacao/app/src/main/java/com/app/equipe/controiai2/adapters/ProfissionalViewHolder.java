package com.app.equipe.controiai2.adapters;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.app.equipe.controiai2.R;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by matheus on 24/06/16.
 */
public class ProfissionalViewHolder extends RecyclerView.ViewHolder {
   // public ImageView ivProfissionalFoto;
    public TextView tvProfissionalNome;
    public TextView tvProfissionalProfissao;
    public TextView tvProfissionalVotos;
    public ImageView ivProfissionalFoto;
    public TextView tvProfissionalData;
    public RelativeLayout profissionais;
    public RelativeLayout publicidade;
    public TextView tvPublicidadeNome;
    public TextView tvPublicidadeProfissao;
    public ImageView ivPublicidadeFoto;
    public ImageView ivEstrela;
    public TextView tvSeguidor;
    public ImageView ivSeguidor;
    public Button bttPublicidadeFoto;
    public ImageView ivVip;
    public  View mView;
    public ProfissionalViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ivVip = (ImageView)itemView.findViewById(R.id.imageView5);
         ivProfissionalFoto = (ImageView) itemView.findViewById(R.id.ivProfissaoFoto);
        tvProfissionalNome = (TextView)itemView.findViewById(R.id.tvProfissionalNome);
        tvProfissionalProfissao = (TextView)itemView.findViewById(R.id.tvProfissionalProfissao);
        tvProfissionalVotos = (TextView) itemView.findViewById(R.id.tv_votos);
        tvProfissionalData = (TextView)itemView.findViewById(R.id.tvProfissionalData);
        profissionais = (RelativeLayout)itemView.findViewById(R.id.profissionais);
        publicidade = (RelativeLayout)itemView.findViewById(R.id.publicidade);
        tvPublicidadeNome = (TextView)itemView.findViewById(R.id.tvPublicidadeNome);
        tvPublicidadeProfissao = (TextView)itemView.findViewById(R.id.tvPublicidadeProfissao);
        ivPublicidadeFoto = (ImageView) itemView.findViewById(R.id.ivPublicidadeFoto);
        bttPublicidadeFoto = (Button)itemView.findViewById(R.id.button_confira);
        ivEstrela = (ImageView)itemView.findViewById(R.id.imageView3);
        tvSeguidor = (TextView)itemView.findViewById(R.id.tv_seguidor);
        ivSeguidor = (ImageView)itemView.findViewById(R.id.imageView4);
        ivVip = (ImageView)itemView.findViewById(R.id.imageView5);
    }


}
