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

public class PedidoOrcamentoquatro extends AppCompatActivity {
    private Toolbar mToolbar;

    private String registros;

    private LinearLayout linear;

    private ProgressBar progressBar;

    private Button casa,apartamento,escritorio,condominio;
    boolean a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamentoquatro);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(4);

        casa = (Button) findViewById(R.id.casa);
        apartamento = (Button) findViewById(R.id.apartamento);
        escritorio = (Button) findViewById(R.id.escritorio);
        condominio = (Button) findViewById(R.id.condominio);


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
        registros += "\nOnde o serviço deve ser realizado?\n ";
      casa.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              registros += "Casa\n";
              Bundle bundle = new Bundle();
              bundle.putString("registros", registros);
              Intent intent = new Intent(getBaseContext(),PedidoOrcamentocinco.class);
              intent.putExtras(bundle);
              startActivity(intent);
          }
      });
      apartamento.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              registros += "Apartamento\n";
              Bundle bundle = new Bundle();
              bundle.putString("registros", registros);
              Intent intent = new Intent(getBaseContext(),PedidoOrcamentocinco.class);
              intent.putExtras(bundle);
              startActivity(intent);
          }
      });
     escritorio.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             registros += "Escritório\n";
             Bundle bundle = new Bundle();
             bundle.putString("registros", registros);
             Intent intent = new Intent(getBaseContext(),PedidoOrcamentocinco.class);
             intent.putExtras(bundle);
             startActivity(intent);
         }

     });
     condominio.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             registros += "Condomínio\n";
             Bundle bundle = new Bundle();
             bundle.putString("registros", registros);
             Intent intent = new Intent(getBaseContext(),PedidoOrcamentocinco.class);
             intent.putExtras(bundle);
             startActivity(intent);
         }
     });
    }

    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
}
