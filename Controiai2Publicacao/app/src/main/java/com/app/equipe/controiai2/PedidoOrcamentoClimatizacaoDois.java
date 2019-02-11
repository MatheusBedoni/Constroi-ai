package com.app.equipe.controiai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class PedidoOrcamentoClimatizacaoDois extends AppCompatActivity {
    private Toolbar mToolbar;
    private ListView lv,lvf;
    //

    //

    private LinearLayout linear;
    private String registros;
    private Button split,janela;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_climatizacao_dois);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
      //  split = (Button)findViewById(R.id.split)
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        split = (Button)findViewById(R.id.split);
        janela = (Button)findViewById(R.id.janela);

        progressBar.setProgress(2);


        //    lvf.setAdapter(adapterFiltro);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                registros =  params.getString("registros");
            }
        }
        registros += "\nTipo de ar-condicionado\n ";
        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Split";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoTres.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        janela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Janela";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoTres.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }




}
