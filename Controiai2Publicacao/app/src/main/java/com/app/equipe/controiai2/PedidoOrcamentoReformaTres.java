package com.app.equipe.controiai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class PedidoOrcamentoReformaTres extends AppCompatActivity {
    private Toolbar mToolbar;
    private ListView lv,lvf;

    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapterFiltro;
    private String profissao;
    private LinearLayout linear;
    private String cidade;
    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_reforma_tres);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(1);



    }


    public void proximo(View view){
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentodois.class);
        startActivity(intent);
    }

}
