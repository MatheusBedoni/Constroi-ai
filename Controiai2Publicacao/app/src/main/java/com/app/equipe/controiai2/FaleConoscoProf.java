package com.app.equipe.controiai2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.equipe.controiai2.domain.Profissional;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import de.cketti.mailto.EmailIntentBuilder;

public class FaleConoscoProf extends AppCompatActivity implements ValueEventListener, Firebase.CompletionListener{
    private Profissional profissional;
    private Toolbar toolbar;
    private TextView nome,email;
    private EditText mensagem,titulo;
    private String mensagemStr,tituloStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fale_conosco_prof);
        //
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Fale conosco");
        toolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        profissional = new Profissional();
        profissional.contextDataDB(this);
        //
        nome = (TextView)findViewById(R.id.txt_nome);
        email = (TextView)findViewById(R.id.txt_email);
        mensagem = (EditText)findViewById(R.id.edt_mensagem);
        titulo = (EditText)findViewById(R.id.edt_titulo);
    }
    //
    public void enviarMensagem(View view){
        mensagemStr = mensagem.getText().toString();
        tituloStr = titulo.getText().toString();
        try{
            EmailIntentBuilder.from(this)
                    .to("app.constroiai@gmail.com")
                    .cc(profissional.getEmail())
                    .subject(tituloStr)
                    .body(mensagemStr)
                    .start();

            finish();
        }catch (Exception e){
            showToast("Algo de errado aconteceu, tente denovo mais tarde");
        }
    }
    //
    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {}
    //
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try{
            Profissional u = dataSnapshot.getValue(Profissional.class);
            profissional = u;
            nome.setText(u.getNome());
            email.setText(u.getEmail());
        }catch (NullPointerException e){}
    }
    //
    public void cancelar(View view){
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return true;
    }
    //
    @Override
    public void onCancelled(FirebaseError firebaseError) {}
}
