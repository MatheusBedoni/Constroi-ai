package com.app.equipe.controiai2;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.equipe.controiai2.auxiliares.VerificaErros;
import com.app.equipe.controiai2.domain.Profissional;

import com.app.equipe.controiai2.util.LibraryClass;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Arrays;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class Entrar extends AppCompatActivity {

    Profissional profissionalu;
    private Firebase firebase;
    private Firebase firebaseUsuario;
    private VerificaErros erros;
    private String emailS;
    private String senhaS;
    private EditText email;
    private EditText senha;
    private FirebaseAuth firebaseAuth;
    Profissional u;
    private ProgressDialog progress;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        email = (EditText)findViewById(R.id.edt_email);
        senha = (EditText)findViewById(R.id.edt_senha);

        erros = new VerificaErros();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        firebaseAuth = FirebaseAuth.getInstance();


        firebase = LibraryClass.getFirebase();
        profissionalu = new Profissional();
        progress = new ProgressDialog(this);
    }

    public void mudarSenha(View view){
        Intent intent = new Intent(this,MudarSenha.class);
        startActivity(intent);
    }
    public void loginFacebook(View view){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email"));
    }
    public void entrar(View view){

        emailS = email.getText().toString();
        senhaS = senha.getText().toString();
        profissionalu.setEmail(emailS);
        profissionalu.setSenha(senhaS);
        if(emailS.trim().isEmpty() || senhaS.trim().isEmpty()){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Há dados em brancos");
            dlg.setNeutralButton("Ok",null);
            dlg.show();
        }else{
            progress.setMessage("Entrando... ");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
            firebase.authWithPassword(
                    profissionalu.getEmail(),
                    profissionalu.getSenha(),
                    new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(final AuthData authData) {
                            profissionalu.saveTokenSP(Entrar.this, authData.getToken());
                            profissionalu.saveIdSP(Entrar.this, authData.getUid());
                            Firebase  firebase = LibraryClass.getFirebase();
                            firebase = firebase.child("users").child(authData.getUid());
                            firebase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Profissional u = dataSnapshot.getValue(Profissional.class);
                                    if(u != null){
                                        finish();
                                        profissionalu.saveTipoSP(Entrar.this,"Usuario");

                                    }else {
                                        finish();
                                        profissionalu.saveTipoSP(Entrar.this,"Profissional");

                                        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                                        startActivity(intent);
                                    }
                                }
                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    showToast(""+erros.getErro(firebaseError.getCode()));
                                    progress.cancel();
                                }
                            });
                            progress.cancel();
                        }
                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            showToast(""+erros.getErro(firebaseError.getCode()));
                            progress.cancel();
                        }
                    }
            );
        }
    }
    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    public void registrar(View view){
        Intent intent = new Intent(this,Registrar.class);
        startActivity(intent);
    }

}
