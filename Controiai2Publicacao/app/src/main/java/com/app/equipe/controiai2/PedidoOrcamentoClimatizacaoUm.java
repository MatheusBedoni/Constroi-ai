package com.app.equipe.controiai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class PedidoOrcamentoClimatizacaoUm extends AppCompatActivity {
    private Toolbar mToolbar;

    //

  private Button instalacao,consertar,limpeza,manutencao;
    private LinearLayout linear;

    private ProgressBar progressBar;
    private String registros = "Tipo de serviço: \n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_climatizacao_um);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(1);
        instalacao = (Button) findViewById(R.id.instalacao);
        consertar = (Button) findViewById(R.id.consertar);
        limpeza = (Button) findViewById(R.id.limpeza);

        //    lvf.setAdapter(adapterFiltro);

        //    lvf.setOnItemClickListener(chamaFiltro());
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
        instalacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Intalação de ar-condicionado\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoDois.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        consertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Consertar ar-condicionado\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoCinco.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        limpeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Limpeza de ar-condicionado\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoDois.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void proximo(View view){
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoDois.class);
        startActivity(intent);
    }


}
