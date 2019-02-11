package com.app.equipe.controiai2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.app.equipe.controiai2.domain.Profissional;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.GregorianCalendar;

import me.drakeet.materialdialog.MaterialDialog;

public class EditarDados extends AppCompatActivity implements Firebase.CompletionListener{
    //
    private Toolbar toolbar;
    private MaterialDialog mMaterialDialog;
    private TextView textoDescricao;
    private CoordinatorLayout coordinatorLayout;
    private int dataInt;
    private EditText experiencias,cursos,tempo,historico,cidades;
    private RelativeLayout relativeLayout1;
    //
    private TextView destaque;
    private InterstitialAd mInterstitialAd;
    String idAdmob="ca-app-pub-2383249025819861/1493882756";
    private Profissional profissional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_dados);
        //
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Descrição");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        experiencias = (EditText)findViewById(R.id.experiencias);
        cursos = (EditText)findViewById(R.id.cursos);
        tempo = (EditText)findViewById(R.id.tempo);
        historico = (EditText)findViewById(R.id.historico);
        cidades = (EditText)findViewById(R.id.edt_cidades);
        textoDescricao = (TextView)findViewById(R.id.texto_descricao);

        relativeLayout1 = (RelativeLayout) findViewById(R.id.relative2);
        //
        profissional = new Profissional();
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
        //
        experiencias.setText(profissional.getExperiencias());
        cursos.setText(profissional.getCursos());
        tempo.setText(profissional.getDiasDisponiveis());
        historico.setText(profissional.getExperiencias());
        cidades.setText(profissional.getBairro());
        //
        if(!profissional.getDescricao().equals(" ")){
            relativeLayout1.setVisibility(View.VISIBLE);
        }


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(idAdmob);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_descricao, menu);
        return true;
    }

    //
    public void alterarDados(){
        //
        String descricao = null;
        String cur = cursos.getText().toString();
        String temp = tempo.getText().toString();
        String cid = cidades.getText().toString();
        descricao = "Experiências: "+experiencias.getText().toString()+"\n";
        //
        if(cid.trim().isEmpty() || cid.trim().isEmpty()){
        }else{
            descricao = descricao +"Cidades/Bairros: "+cidades.getText().toString()+"\n";
        }
        //
        if(cur.trim().isEmpty() || cur.trim().isEmpty()){
        }else{
            descricao = descricao +"Cursos: "+cursos.getText().toString()+"\n";
        }
        //
        if(temp.trim().isEmpty() || temp.trim().isEmpty()){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Você não escreveu seus dias disponiveis");
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }else{
            descricao = descricao + "Dias Disponiveis: "+tempo.getText().toString()+"\n";
            //
            profissional.setBairro(cidades.getText().toString());
            profissional.setExperiencias(experiencias.getText().toString());
            profissional.setCursos(cursos.getText().toString());
            profissional.setDiasDisponiveis(tempo.getText().toString());
            profissional.setDescricao(descricao);
            //profissional.setData_entrada(dataInt);
            //

            profissional.updateTodosProfDB(this);
            //
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

            }
            Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
            startActivity(intent);
        }
    }
    //
    public void salvarhis(View view){
        String descricao = null;
        if( historico.getText().toString() != null){
            descricao = experiencias.getText().toString()+"\n";
        }
        //
        profissional.setExperiencias(descricao);
        profissional.setDescricao(descricao);
        //
        profissional.updateDB(this);
        profissional.updateTodosDB(this);
        profissional.updateTodosProfDB(this);
        //
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        startActivity(intent);
    }
    //
    public void salvar1(){
        try{
            String exp = experiencias.getText().toString();
            if(exp.trim().isEmpty() || exp.trim().isEmpty()){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("Você não escreveu suas experiencias");
                dlg.setNeutralButton("Ok",null);
                dlg.show();
            }else{
                String descricao = null;
                String cur = cursos.getText().toString();
                String temp = tempo.getText().toString();
                String cid = cidades.getText().toString();
                descricao = "Experiências: "+experiencias.getText().toString()+"\n";
                //
                if(cid.trim().isEmpty() || cid.trim().isEmpty()){
                }else{
                    descricao = descricao +"Cidades/Bairros: "+cidades.getText().toString()+"\n";
                }
                //
                if(cur.trim().isEmpty() || cur.trim().isEmpty()){
                }else{
                    descricao = descricao +"Cursos: "+cursos.getText().toString()+"\n";
                }
                //
                if(temp.trim().isEmpty() || temp.trim().isEmpty()){
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setMessage("Você não escreveu seus dias disponiveis");
                    dlg.setNeutralButton("Ok",null);
                    dlg.show();
                }else{
                    descricao = descricao + "Dias Disponiveis: "+tempo.getText().toString()+"\n";
                    //
                    profissional.setBairro(cidades.getText().toString());
                    profissional.setExperiencias(experiencias.getText().toString());
                    profissional.setCursos(cursos.getText().toString());
                    profissional.setDiasDisponiveis(tempo.getText().toString());
                    profissional.setDescricao(descricao);
                    //profissional.setData_entrada(dataInt);
                    //

                    profissional.updateTodosProfDB(this);
                    //
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {

                    }
                    Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                    startActivity(intent);
                }
            }
        }catch (Exception e){

        }

    }
    //
    public void pular1(View view){
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        intent.putExtra("profissional",profissional);
        startActivity(intent);
        finish();
    }
    //
    public void negDescricao(View view){
         mMaterialDialog = new MaterialDialog(this)
                .setTitle("Cancelar descrição")
                .setMessage("Você tem certeza disso? A descrição é um ponto muito importante")
                .setPositiveButton("SIM", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                        intent.putExtra("profissional",profissional);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NÂO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
        mMaterialDialog.show();
    }

    //
    public void pular2(View view){
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        intent.putExtra("profissional",profissional);
        startActivity(intent);
        finish();
    }
    //
    public AdapterView.OnItemClickListener chamaProfissoes(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        };
    }
    //
    public void criarDescricao(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {

        }
            relativeLayout1.setVisibility(View.VISIBLE);
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        //
        int id = item.getItemId();
        if(id == android.R.id.home){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {

            }
            Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
            intent.putExtra("profissional",profissional);
            startActivity(intent);
            finish();
        }
        if(id == R.id.action_fale){
            salvar1();
        }
        return true;
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if(firebaseError != null){
           showToast("Alguma coisa não deu certo");
        }else{
           showToast("As alterações foram salvas");
        }
    }
    //
    protected void showToast( String message ){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
