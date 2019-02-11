package com.app.equipe.controiai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PedidoOrcamentocinco extends AppCompatActivity {
    private Toolbar mToolbar;


    //
    private String registros;
    private Button semana,urgente;
    boolean a,b;
    private LinearLayout linear;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamentocinco);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(5);
        semana = (Button) findViewById(R.id.semana);
        urgente = (Button) findViewById(R.id.urgente);


        //    lvf.setOnItemClickListener(chamaFiltro());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
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
        registros += "\nQuando o serviço deve ser realizado?\n ";
        semana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Esta semana\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoseis.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        urgente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Urgente\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentoseis.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
    public void proximo(View view){
        Bundle bundle = new Bundle();
        bundle.putString("registros", registros);
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentoseis.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

}
