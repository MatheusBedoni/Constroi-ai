package com.app.equipe.controiai2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.app.equipe.controiai2.domain.Credito;
import com.app.equipe.controiai2.domain.Profissional;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class DestaqueRoraima extends AppCompatActivity implements ValueEventListener,Firebase.CompletionListener{
    private Profissional profissional;
    private Toolbar toolbar;

    private java.lang.String vendedor =
            "id_vend_princ:1234567890123456,\n" +
                    "id_vend_sec:123,\n" +
                    "token_vend_princ:01,\n" +
                    "porc_vend_princ:2013,\n" +
                    "porc_vend_secund:"
                   ;
    private  java.lang.String comprador =
            "cpf:1234567890123456,\n" +
                    "nome:123,\n" +
                    "dt_nasc:01,\n" +
                    "telefone:2013,\n" +
                    "email:\n"+
                    "cep: \n"+
                    "num: \n"+
                    "comp:"
            ;

    private  java.lang.String pedido =
            "num_pedido_vend:1234567890123456,\n" +
                    "desc_pedido:123,\n" +
                    "valor_total:01,\n" +
                    "moeda:2013,\n" +
                    "forma_pagam:\n"+
                    "cobrança:";

    private java.lang.String registros =  "nomecompleto: ,\n" +
            "numerocartao:,\n" +
            "cvccartao:,\n" +
            " escartao:,\n" +
            "anocartao:,\n" +
            "ckproprietario: ,\n" +
            "cpf:,\n" +
            "dtnascimento: ,\n" +
            "codigopais: ,\n" +
            "codigoestado: ,\n" +
            "fonecelular: ,\n" +
            "parcelas: ,\n" +
            "bandeiracartao: ,\n" +
            "inscricao: ,\n" +
            "participante: ,\n" +
            "evento: ,\n" +
            "banco: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destaque_roraima);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Destaque");
        toolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("profissional") != null) {
            profissional = getIntent().getExtras().getParcelable("profissional");
        } else {
        }

    }

    public void gratuito(View view){
//        Intent intent = new Intent(getBaseContext(),DestaqueActivity.class);
//        intent.putExtra("profissional", profissional);
//        startActivity(intent);
    }
    public void queroservip(View view){
//        profissional.setTerminoVip(666);
//        profissional.updateDB(this);
//        profissional.updateTodosDB(this);
//        profissional.updateTodosProfDB(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(DestaqueRoraima.this);
        builder.setTitle("Ficamos felizes por você escolher ser um profissional VIP.");
        builder.setMessage("Nossa equipe entrará em contato com você em até 24 horas para te informar todos os detalhes sobre o profissional VIP e o pagamento ");
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        ArrayList<String> list = new ArrayList<>();
                        ArrayList<String> cobraca = new ArrayList<>();
                        list.add(vendedor);
                        list.add(comprador);
                        list.add(pedido);
                        Gson gson = new GsonBuilder().registerTypeAdapter(Credito.class, new CreditoDes()).create();
                        Retrofit retrofit = new Retrofit
                                .Builder()
                                .baseUrl("https://app.ticketphone.com.br/webrun/")
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();
                        PagamentoService pagamentoService = retrofit.create(PagamentoService.class);
                        Call<Credito> call = pagamentoService.enviardados(1152,"4K3TB239FREADFU420KS2TJPKDDF734",list);
                        call.enqueue(new Callback<Credito>() {
                            @Override
                            public void onResponse(Call<Credito> call, retrofit2.Response<Credito> response) {

                                Log.i("LOG", "Saveretrofit: " + response.isSuccessful());

                            }

                            @Override
                            public void onFailure(Call<Credito> call, Throwable t) {
                                Log.i("LOG", "Error SAVE: " + t.getMessage());
                            }
                        });
                        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                        startActivity(intent);
                    }
                });
        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    public void continuarsendovip(View view){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        finish();
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        intent.putExtra("profissional", profissional);
        startActivity(intent);
        return true;
    }
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
