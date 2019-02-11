package com.app.equipe.controiai2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.equipe.controiai2.R;
import com.app.equipe.controiai2.domain.Chamadas;


import java.util.ArrayList;

/**
 * Created by matheus on 27/09/16.
 */
public class ChamadasAdapter extends RecyclerView.Adapter<ChamadasAdapter.MyViewHolder2> {
    private Context context;
    private ArrayList<Chamadas> chamadas;

    public ChamadasAdapter(Context c, ArrayList<Chamadas> ch){
        chamadas = ch;
        context = c;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = LayoutInflater.from(context).inflate(R.layout.layout_chamadas, parent, false);
        MyViewHolder2 viewHolder2 = new MyViewHolder2(context,v);
        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        Chamadas c = chamadas.get(position);
        holder.tvNome.setText(c.getNome());
        holder.tvTelefone.setText(c.getTel());
        holder.tvProblema.setText(c.getProblema());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        public Context context;
        public TextView tvNome;
        public TextView tvTelefone;
        public TextView tvProblema;
        public Button bttTelefone;
        public Button bttExcluir;

        public MyViewHolder2(Context context,View itemView) {
            super(itemView);
            this.context = context;
            tvNome = (TextView) itemView.findViewById(R.id.nome);
            tvTelefone = (TextView) itemView.findViewById(R.id.txt_phone);
            tvProblema = (TextView) itemView.findViewById(R.id.txt_problema);
            bttTelefone = (Button) itemView.findViewById(R.id.imageView5);
            bttExcluir = (Button)itemView.findViewById(R.id.imageView6);

        }
    }
}
