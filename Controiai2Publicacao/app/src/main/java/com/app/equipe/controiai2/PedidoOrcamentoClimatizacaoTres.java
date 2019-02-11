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

public class PedidoOrcamentoClimatizacaoTres extends AppCompatActivity {
    private Toolbar mToolbar;
    private ListView lv,lvf;
    //

    // private String profissao;
    private LinearLayout linear;
    private String cidade;
    private ProgressBar progressBar;
    private String registros;
    private Button nove,doze,dezoito,vintequatro,maisvintequatro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_climatizacao_tres);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(3);
        nove = (Button)findViewById(R.id.nove);
        doze = (Button)findViewById(R.id.doze);
        dezoito = (Button)findViewById(R.id.dezoito);
        vintequatro = (Button)findViewById(R.id.vintequatro);
        maisvintequatro = (Button)findViewById(R.id.maisvintequatro);


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
        registros += "\nQuantos BTUs\n ";
        nove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Até 9000 BTUS\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        doze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Até 12000 BTUs\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        dezoito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Até 18000 BTUs\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        vintequatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Até 24000 BTUs\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        maisvintequatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Mais 24000 BTUs\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void proximo(View view){
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoQuatro.class);
        startActivity(intent);
    }


}
