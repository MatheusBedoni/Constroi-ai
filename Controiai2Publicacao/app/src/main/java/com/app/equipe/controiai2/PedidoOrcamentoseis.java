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

public class PedidoOrcamentoseis extends AppCompatActivity {
    private Toolbar mToolbar;


    private String registros;
    private LinearLayout linear;

    private ProgressBar progressBar;
    private Button manha,tarde,noite,comercial,indiferente;
    boolean a,b,c,d,e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamentoseis);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(6);

        manha = (Button) findViewById(R.id.manha);
        tarde = (Button) findViewById(R.id.tarde);
        noite = (Button) findViewById(R.id.noite);
        comercial = (Button) findViewById(R.id.comercial);
        indiferente = (Button) findViewById(R.id.indiferente);


    }

    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
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
        registros += "\nQual o melhor horario?\n ";
        manha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Manhã\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        tarde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Tarde\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        noite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Noite\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        comercial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Comercial\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        indiferente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registros += "Indiferente\n";
                Bundle bundle = new Bundle();
                bundle.putString("registros", registros);
                Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void proximo(View view){
        Bundle bundle = new Bundle();
        bundle.putString("registros", registros);
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentosete.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
