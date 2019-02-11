package com.app.equipe.controiai2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class PedidoOrcamentodois extends AppCompatActivity {
    private Toolbar mToolbar;



    private LinearLayout linear;

    private ProgressBar progressBar;

    private String registros;

    private Switch aSwitch1,aSwitch2,aSwitch3,aSwitch4,aSwitch5,aSwitch6,aSwitch7,aSwitch8;
    boolean a,b,c,d,e,f,g,h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamentodois);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(2);

        aSwitch1 = (Switch)findViewById(R.id.switch1);
        aSwitch2 = (Switch)findViewById(R.id.switch2);
        aSwitch3 = (Switch)findViewById(R.id.switch3);
        aSwitch4 = (Switch)findViewById(R.id.switch4);
        aSwitch5 = (Switch)findViewById(R.id.switch5);
        aSwitch6 = (Switch)findViewById(R.id.switch6);
        aSwitch7 = (Switch)findViewById(R.id.switch7);
        aSwitch8 = (Switch)findViewById(R.id.switch8);
        //    lvf.setAdapter(adapterFiltro);

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
        registros += "\nÁreas do imóvel que precisa de ajuda: ";
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Banheiro\n";
            }

        });
        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showToast("switch: "+isChecked );
                b = isChecked;
                registros += "Cozinha\n";
            }

        });
        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Lavanderia\n";
            }

        });
        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Sala\n";
            }

        });
        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Quarto\n";
            }

        });

        aSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Sacada\n";
            }

        });
        aSwitch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Garagem\n";
            }

        });
        aSwitch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Pátio / Área externa\n";
            }

        });
    }

    public void proximo(View view){
        Bundle bundle = new Bundle();
        bundle.putString("registros", registros);
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentotres.class);
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
