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

public class PedidoOrcamentotres extends AppCompatActivity {
    private Toolbar mToolbar;

    private LinearLayout linear;
    private ProgressBar progressBar;
    private String registros;

    private Switch aSwitch1,aSwitch2,aSwitch3,aSwitch4,aSwitch5,aSwitch6,aSwitch7,aSwitch8,aSwitch9,aSwitch10,aSwitch11,aSwitch12,aSwitch13,aSwitch14,aSwitch15,aSwitch16,aSwitch17,aSwitch18,aSwitch19,aSwitch20,aSwitch21,aSwitch22;
    boolean a,b,c,d,e,f,g,h,i,j,l,m,n,o,p,q,r,s,t,u,v,x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_orcamentotres);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        linear = (LinearLayout)findViewById(R.id.linear);

        progressBar.setProgress(3);
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
        aSwitch15 = (Switch)findViewById(R.id.switch15);
        aSwitch16 = (Switch)findViewById(R.id.switch16);
        aSwitch17 = (Switch)findViewById(R.id.switch17);
        aSwitch18 = (Switch)findViewById(R.id.switch18);
        aSwitch19 = (Switch)findViewById(R.id.switch19);
        aSwitch20 = (Switch)findViewById(R.id.switch20);
        aSwitch21 = (Switch)findViewById(R.id.switch21);
        aSwitch22 = (Switch)findViewById(R.id.switch22);


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
        registros += "\nItens que precisa de ajuda?\n ";
        aSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Cortina\n";
            }

        });
        aSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Moveis\n";
            }

        });
        aSwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Ventilador\n";
            }

        });
        aSwitch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Torneira\n";
            }

        });
        aSwitch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Quadros\n";
            }

        });

        aSwitch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Portas\n";
            }

        });
        aSwitch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Eletrodomésticos\n";
            }

        });
        aSwitch8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Vaso sanitário\n";
            }

        });
        aSwitch9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Pia\n";
            }

        });
        aSwitch10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Luminarias\n";
            }

        });
        aSwitch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Tomadas\n";
            }

        });
        aSwitch12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Chuveiro\n";
            }

        });
        aSwitch13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Ventilador de teto\n";
            }

        });

        aSwitch14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Varão de cortina\n";
            }

        });
        aSwitch15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Persianas\n";
            }

        });
        aSwitch16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Varal\n";
            }

        });

        aSwitch17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Suporte para TV\n";
            }

        });
        aSwitch18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Armários\n";
            }

        });

        aSwitch19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Prateleiras\n";
            }

        });
        aSwitch20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Barras de apoio\n";
            }

        });
        aSwitch21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Disjuntor\n";
            }

        });
        aSwitch22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                registros += "Fechadura\n";
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

    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }

}
