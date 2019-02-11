package com.app.equipe.controiai2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.app.equipe.controiai2.auxiliares.VerificaErros;

import com.app.equipe.controiai2.domain.Profissional;
import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.Map;
import android.widget.Spinner;

import de.cketti.mailto.EmailIntentBuilder;

public class Profissao extends AppCompatActivity {
    //
    private Toolbar mToolbar;
    private ProgressDialog progress;
    private FrameLayout frame;
    private LinearLayout caa;
    private Button comecar_cadastro;
    private Button cancelar_cadastro;
    private  AlertDialog.Builder dlg;
    //
    int a = 0,c = 0,ec = 0,e = 0,p = 0,pe = 0,en = 0,vi = 0,fu = 0,rr = 0,me,se;
    private int i = 0;
    private String profissao;;
    //
    private Spinner spinner1, spinner2,spinner3;
    private Profissional profissional;
    private Firebase firebase;
    private VerificaErros erros;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissao);
        //
        mToolbar = (Toolbar)findViewById(R.id.tb_main);
        mToolbar.setTitle("Qual é a sua profissão?");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        //
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);

        profissional = new Profissional();
        progress = new ProgressDialog(this);
        firebase = LibraryClass.getFirebase();
        erros = new VerificaErros();
        //
        comecar_cadastro = (Button)findViewById(R.id.comecar_cadastro);
        cancelar_cadastro = (Button)findViewById(R.id.cancel_cadastro);
        dlg = new AlertDialog.Builder(this);
        frame = (FrameLayout)findViewById(R.id.termos);

    }
    //
    @Override
    protected void onResume() {
        super.onResume();
        //
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("profissional") != null) {
            profissional = getIntent().getExtras().getParcelable("profissional");
        } else {
            Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    //
    public void salvar(){
        String profissao1 = "1";
        String profissao2 = "2";
        String profissao3 = "3";
        if (!String.valueOf(spinner1.getSelectedItem()).equals("Nenhuma profissão")) {
            profissao1 = String.valueOf(spinner1.getSelectedItem());
        }
        if (!String.valueOf(spinner2.getSelectedItem()).equals("Nenhuma profissão")) {
            profissao2 = String.valueOf(spinner2.getSelectedItem());
        }
        if (!String.valueOf(spinner3.getSelectedItem()).equals("Nenhuma profissão")) {
            profissao3 = String.valueOf(spinner3.getSelectedItem());
        }
    if(profissao1.equals("1")) {
        dlg.setMessage("Escolha sua profissão principal");
        dlg.setNeutralButton("Ok", null);
        dlg.show();
    }else{
        if(!profissao1.equals(profissao2) && !profissao2.equals(profissao3) && !profissao3.equals(profissao1)){
            profissional.setProfissao(profissao1);
            if(profissao2.equals("2")){
            }else{
                profissional.setProfissao(profissional.getProfissao()+", " + profissao2);
                if(profissao3.equals("3")){
                }else{
                    profissional.setProfissao(profissional.getProfissao()+", " + profissao3);
                }
            }

            try{
                profissional.setDadosQueryTodos(profissional.getEstado()+"_"+profissional.getCidade());
                profissional.setDadosQuery(profissional.getEstado()+"_"+profissional.getCidade()+"_"+profissao1);
            }catch (Exception e){

            }
            // showToast("Profissao final "+profissional.getProfissao());
            //
            profissional.setData_entrada(-20170719);

            frame.setVisibility(View.VISIBLE);
            //
            comecar_cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    carregar();
                    //
                    firebase.createUser(
                            profissional.getEmail(),
                            profissional.getSenha(),
                            new Firebase.ValueResultHandler<Map<String, Object>>() {
                                @Override
                                public void onSuccess(Map<String, Object> stringObjectMap) {
                                    //
                                    profissional.setId(stringObjectMap.get("uid").toString());
                                    profissional.saveTodosDB();
                                   // profissional.saveDB("");
                                    //profissional.saveTodosEstadoDB();
                                    //

                                    //

                                    firebase.unauth();
                                    progress.cancel();
                                    //
                                    Intent intent = new Intent(getBaseContext(), Entrar.class);
                                    startActivity(intent);
                                    finish();
                                    showToast("Conta  criada com sucesso");
                                }
                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    Intent intent = new Intent(getBaseContext(), Entrar.class);
                                    startActivity(intent);
                                    //
                                    showToast(""+erros.getErro(firebaseError.getCode()));
                                    finish();
                                    //
                                    progress.cancel();
                                }
                            }
                    );
                }
            });
            cancelar_cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //
                    Intent intent = new Intent(getBaseContext(), Entrar.class);
                    startActivity(intent);
                    //
                    finish();
                    showToast("Seu registro foi cancelado");
                }
            });
        }else{
            dlg.setMessage("As profissões não podem ser repetidas!");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
        return true;
    }



    //
//    public void cancelar(View view){
//        Intent intent = new Intent(getBaseContext(), Entrar.class);
//        startActivity(intent);
//        showToast("Seu registro foi cancelado :(");
//        finish();
//    }
    //
    public void carregar(){
        progress.setMessage("Salvando... ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();
    }
    //
    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    //
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        if( id == R.id.action_fale){
            salvar();
        }
        return true;
    }
    //
    public void termo(View view){
        Uri uri = Uri.parse("http://appconstroiai.wixsite.com/appdonorte/termos-de-uso");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
