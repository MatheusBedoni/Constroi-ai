package com.app.equipe.controiai2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.app.equipe.controiai2.domain.Chamadas;

import java.util.GregorianCalendar;
import java.util.Random;

public class PedidoOrcamentoFim extends AppCompatActivity {
    private Toolbar mToolbar;


    private Chamadas chamadas;
    private EditText comente;
    private String registros;
    private LinearLayout linear;
    private ProgressBar progressBar;

    private EditText edt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_fim);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //

        chamadas = new Chamadas();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);
        edt = (EditText) findViewById(R.id.edt);

        progressBar.setProgress(8);


        //    lvf.setAdapter(adapterFiltro);

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

    public void proximo(View view){
        if(edt.getText().toString().trim().isEmpty() ){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Você precisa nos informar seu telefone para podermos entrar em contato com você");
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }else{
            GregorianCalendar gc = new GregorianCalendar();
            String mes, dia;
            if (gc.get(gc.MONTH) >= 0 && gc.get(gc.MONTH) <= 9) {
                int mesint = gc.get(gc.MONTH) + 1;
                mes = "0" + mesint;
            } else {
                int mesint = gc.get(gc.MONTH) + 1;
                mes = "" + mesint;
            }
            if (gc.get(gc.DAY_OF_MONTH) >= 1 && gc.get(gc.DAY_OF_MONTH) < 10) {
                dia = "0" + gc.get(gc.DAY_OF_MONTH);
            } else {
                dia = "" + gc.get(gc.DAY_OF_MONTH);
            }
            String data = "" + gc.get(gc.YEAR) + "/" + mes + "/" + dia;

            Random random = new Random();
            int x = random.nextInt(1000);
            chamadas.setProblema(registros);
            chamadas.setData(data);
            chamadas.setTel(edt.getText().toString()+"\n");
            chamadas.saveChamadasDB(edt.getText().toString()+x,"1");
            Intent intent = new Intent(getBaseContext(),OrcamentoCompleto.class);
            startActivity(intent);
        }

    }
}
