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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;

public class PedidoOrcamentoReformaDois extends AppCompatActivity {
    private Toolbar mToolbar;
    private ListView lv,lvf;


    private LinearLayout linear;

    private String registros;

    private ProgressBar progressBar;

    private Switch aSwitch1,aSwitch2,aSwitch3,aSwitch4,aSwitch5,aSwitch6,aSwitch7,aSwitch8,aSwitch9,aSwitch10,aSwitch11,aSwitch12,aSwitch13,aSwitch14,aSwitch15,aSwitch16,aSwitch17,aSwitch18,aSwitch19,aSwitch20,aSwitch21,aSwitch22;
    boolean a,b,c,d,e,f,g,h,i,j,l,m,n,o,p,q,r,s,t,u,v,x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamento_reforma_dois);
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
        aSwitch9 = (Switch)findViewById(R.id.switch9);
        aSwitch10 = (Switch)findViewById(R.id.switch10);
        aSwitch11 = (Switch)findViewById(R.id.switch11);
        aSwitch12 = (Switch)findViewById(R.id.switch12);
        aSwitch13 = (Switch)findViewById(R.id.switch13);
        aSwitch14 = (Switch)findViewById(R.id.switch14);

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

        registros += "Itens que precisa de ajuda: \n";
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Telhado\n";
            }

        });
        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Parede\n";
            }

        });
        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Piso\n";
            }

        });
        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Janela\n";
            }

        });
        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Cozinha\n";
            }

        });

        aSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Sala\n";
            }

        });
        aSwitch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Quarto\n";
            }

        });
        aSwitch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Garagem\n";
            }

        });
        aSwitch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Área Externa\n";
            }

        });
        aSwitch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Banheiro\n";
            }

        });
        aSwitch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Sacada\n";
            }

        });
        aSwitch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Lavanderia\n";
            }

        });
        aSwitch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Muro\n";
            }

        });

        aSwitch14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Porta\n";
            }

        });
    }

    public void proximo(View view){
        Bundle bundle = new Bundle();
        bundle.putString("registros", registros);
        Intent intent = new Intent(getBaseContext(),PedidoOrcamentoquatro.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
