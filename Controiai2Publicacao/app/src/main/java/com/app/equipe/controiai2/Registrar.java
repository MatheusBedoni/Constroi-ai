package com.app.equipe.controiai2;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.app.equipe.controiai2.auxiliares.VerificaErros;

import com.app.equipe.controiai2.domain.Profissional;

import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.GregorianCalendar;
import java.util.Map;

import de.cketti.mailto.EmailIntentBuilder;

public class Registrar extends AppCompatActivity implements  Firebase.CompletionListener {
    //
    private Toolbar mToolbar;

    private EditText nome;
    private EditText email;
    private EditText tel;
    private EditText senha;
    private EditText senhacom;
    private EditText site;
    private EditText ddd;
    private ProgressDialog progress;
    private  AlertDialog.Builder dlg;
    private Button comecar_cadastro;
    private Button cancelar_cadastro;
    //
    private Profissional profissionalu;
    private Firebase firebase;

    private VerificaErros erros;

    private FrameLayout frame;
    //
    private String nomeStr;
    private String emailStr;
    private String telStr;
    private String senhaStr;
    private String senhacomStr;
    private String siteStr;
    private String dddStr;
    private String cidade;
    private String estado;
    //
    private int dataInt;
    int i = 4;
    private int escolha = 0;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        //
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Registrar");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        progress = new ProgressDialog(this);
        dlg = new AlertDialog.Builder(this);
        comecar_cadastro = (Button)findViewById(R.id.comecar_cadastro);
        cancelar_cadastro = (Button)findViewById(R.id.cancel_cadastro);

        frame = (FrameLayout)findViewById(R.id.termos);
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        tel = (EditText) findViewById(R.id.tel);
        senha = (EditText) findViewById(R.id.senha);
        senhacom = (EditText) findViewById(R.id.senhacom);
        site = (EditText) findViewById(R.id.site);
        ddd = (EditText) findViewById(R.id.ddd);
        //
        profissionalu = new Profissional();
        firebase = LibraryClass.getFirebase();

        erros = new VerificaErros();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            finish();

        }
        if(id == R.id.action_fale){
            //
            if (escolha == 0) {
                dlg.setMessage("Você precisa marcar uma das opções");
                dlg.setNeutralButton("Ok", null);
                dlg.show();
            } else {
                nomeStr = nome.getText().toString();
                emailStr = email.getText().toString();
                //
                telStr = tel.getText().toString();
                dddStr = ddd.getText().toString();
                //

                //
                if (emailStr.trim().isEmpty() || nomeStr.trim().isEmpty() || telStr.trim().isEmpty() || dddStr.trim().isEmpty() ) {
                    dlg.setMessage("Você precisa nos informar os dados");
                    dlg.setNeutralButton("Ok", null);
                    dlg.show();
                } else {
                    dddStr = "(" + dddStr + ") ";
                    try{
                        StringBuilder stringBuilder = new StringBuilder(telStr);
                        stringBuilder.insert(telStr.length() - 4, '-');
                        telStr = stringBuilder.toString();
                        telStr = dddStr + telStr;
                    }catch (Exception e){
                    }
                    //
                    senhaStr = senha.getText().toString();
                    senhacomStr = senhacom.getText().toString();
                    //
                    if (senhaStr.trim().isEmpty() || senhacomStr.trim().isEmpty()) {
                        dlg.setMessage("Você precisa de uma senha");
                        dlg.setNeutralButton("Ok", null);
                        dlg.show();
                    } else {
                        if(senhaStr.length() > 5) {
                            //
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
                            String data = "" + gc.get(gc.YEAR) + "" + mes + "" + dia;
                            dataInt = Integer.parseInt(data);
                            dataInt *= -1;
                            //
                            profissionalu.setNome(nomeStr);
                            profissionalu.setEmail(emailStr);
                            profissionalu.setTel(telStr);
                            profissionalu.setSenha(senhaStr);
                            profissionalu.setCidade(cidade);
                            profissionalu.setData_entrada(-20170719);
                            profissionalu.setEstado(estado);
                            //
                            if (senhaStr.equals(senhacomStr)) {
                                if (escolha == 1) {
                                    //
                                    Intent intent = new Intent(getBaseContext(), Profissao.class);
                                    intent.putExtra("profissional", profissionalu);
                                    startActivity(intent);
                                } else if (escolha == 2) {

                                    frame.setVisibility(View.VISIBLE);
                                    //
                                    comecar_cadastro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //

                                        }
                                    });
                                    //
                                    cancelar_cadastro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            finish();
                                            showToast("Seu registro foi cancelado");
                                        }
                                    });
                                    //
                                } else {

                                    frame.setVisibility(View.VISIBLE);
                                    //
                                    comecar_cadastro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //
                                            carregar();
                                            //
                                            profissionalu.setProfissao("Construtora");
                                            profissionalu.setSite(siteStr);
                                            profissionalu.setDadosQueryTodos(profissionalu.getEstado()+"_"+profissionalu.getCidade());
                                            profissionalu.setDadosQuery(profissionalu.getEstado()+"_"+profissionalu.getCidade()+"_"+"Construtora");
                                            //
                                            firebase.createUser(
                                                    profissionalu.getEmail(),
                                                    profissionalu.getSenha(),
                                                    new Firebase.ValueResultHandler<Map<String, Object>>() {
                                                        @Override
                                                        public void onSuccess(Map<String, Object> stringObjectMap) {
                                                            //
                                                            profissionalu.setId(stringObjectMap.get("uid").toString());
                                                            progress.dismiss();
                                                            profissionalu.saveTodosDB();
//                                                            profissionalu.saveDB("");
//                                                            profissionalu.saveTodosEstadoDB();

                                                            Intent intent = new Intent(getBaseContext(), Entrar.class);
                                                            startActivity(intent);
                                                            finish();
                                                            showToast("Conta  criada com sucesso");
                                                            firebase.unauth();
                                                        }
                                                        @Override
                                                        public void onError(FirebaseError firebaseError) {
                                                            progress.dismiss();
                                                            showToast(""+erros.getErro(firebaseError.getCode()));
                                                            finish();
                                                        }
                                                    }
                                            );
                                        }
                                    });
                                    //
                                    cancelar_cadastro.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            finish();
                                            showToast("Seu registro foi cancelado");
                                        }
                                    });
                                    //
                                }
                            } else {
                                dlg.setMessage("As senha não correspondem");
                                dlg.setNeutralButton("Ok", null);
                                dlg.show();
                            }
                        }else{
                            showToast("Escolha uma senha com mais de cinco caracteres");
                        }
                    }
                }

            }
        }
        return true;
    }
    //
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
       cidade = "Boa Vista e região";
       estado = "Roraima";
    }

    //
    public void carregar(){
        progress.setMessage("Salvando... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }
    //
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_profissionais:
                if (checked) {
                    escolha = 1;
                }
                break;

            case R.id.radio_construtora:
                if (checked) {
                    escolha = 3;
                }
                break;
        }
    }
    //
    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    //
    public void cancelar(View view){
        finish();
    }
    //
    public void termo(View view){
        Uri uri = Uri.parse("http://appconstroiai.wixsite.com/appdonorte/termos-de-uso");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {}
}
