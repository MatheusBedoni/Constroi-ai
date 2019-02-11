package com.app.equipe.controiai2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.app.equipe.controiai2.adapters.ChamadasFirebaseAdapter;
import com.app.equipe.controiai2.adapters.ChamadasViewHolder;

import com.app.equipe.controiai2.domain.Chamadas;
import com.app.equipe.controiai2.domain.Profissional;

import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import me.drakeet.materialdialog.MaterialDialog;

public class ProfissionalChamadas extends AppCompatActivity implements ValueEventListener,Firebase.CompletionListener{
    private Toolbar toolbar;
    private RecyclerView rvUsers;
    private LinearLayout lista;
    private LinearLayout feed;
    private MaterialDialog mMaterialDialog;
    public Button button_tel;
    //
    private Firebase firebase;
    private ChamadasFirebaseAdapter adapter;
    private Profissional profissional,profissionalU;
    private Chamadas chamadasP;
    //
    //
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_chamadas);

        //
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Chamadas");
        toolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //
        lista = (LinearLayout)findViewById(R.id.lista);
        feed = (LinearLayout)findViewById(R.id.feed);

        rvUsers = (RecyclerView) findViewById(R.id.rv_list);
        //
        profissionalU = new Profissional();
        chamadasP = new Chamadas();

        profissional = new Profissional();

        profissional.contextDataDB(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
    public void atualizarChamada(){
        chamadasP.update(profissional.getId(),this);
    }
    public void removerChamada(){
        chamadasP.removeDB(profissional.getId(),chamadasP.getId());
    }
    //
    private void init() {
        rvUsers.setHasFixedSize(true);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        //
        adapter = new ChamadasFirebaseAdapter(
                Chamadas.class,
                R.layout.layout_chamadas,
                ChamadasViewHolder.class,
                firebase){
            @Override
            public void onClickListener(View view, int position) {}
            @Override
            protected void populateViewHolder(ChamadasViewHolder useViewHolder, final Chamadas chamada, int i) {
                if(chamada.getProblema().equals("Telefone") || chamada.getProblema().equals("Whatsapp")){
                    useViewHolder.tvProb.setText("Chamada por ");
                }
                useViewHolder.tvData.setText(chamada.getData());
                useViewHolder.tvNome.setText(chamada.getNome());
                useViewHolder.tvTelefone.setText(chamada.getTel());
                useViewHolder.tvProblema.setText(chamada.getProblema());
                    Glide
                            .with(getBaseContext())
                            .load(chamada.getUrl())
                            .thumbnail(0.5f)
                            .centerCrop()
                            .placeholder(R.drawable.user_male_black_shape__1_)
                            .crossFade(50)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(useViewHolder.ivFotoUsuario);


                useViewHolder.bttAvaliacao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                        String[] separado = chamada.getTel().split(" 9");
                        String number = "55"+separado[0] + separado[1];
                        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                        if (isWhatsappInstalled) {
                            Intent sendIntent = new Intent("android.intent.action.MAIN");
                            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(number) + "@s.whatsapp.net");//phone number without "+" prefix
                            startActivity(sendIntent);
                        }else {
                            showToast("Whatsapp não está instalado!");
                            Uri uri = Uri.parse("market://details?id=com.whatsapp");
                            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(goToMarket);

                        }

                    }catch (Exception e){
                        showToast("Aconteceu um erro ao tentar abrir");
                    }
                }

            });
                useViewHolder.bttTelefonar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" +chamada.getTel()));
                            startActivity(intent);
                        }catch (Exception e){
                            showToast("Aconteceu um erro ao tentar abrir");
                        }
                    }

                });


            }
        };
        rvUsers.setAdapter(adapter);
        rvUsers.setOnClickListener(new RecyclerView.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
        profissional.setVip(-12);
    }
    //
    protected void showToast( String message ){
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    //
    public void atualizarProf(){
        profissionalU.updateDB(this);
        profissionalU.updateTodosDB(this);
        profissionalU.updateTodosProfDB(this);

    }
    //

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
            intent.putExtra("profissional", profissional);
            startActivity(intent);
        }
        return true;
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if(firebaseError != null){
        }else{
        }
    }
    //
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
       try{
           Profissional u = dataSnapshot.getValue(Profissional.class);
           profissional = u;
           //

               firebase = LibraryClass.getFirebase().child("orcamento");
               init();

       }catch (NullPointerException e){}
    }
    //
    @Override
    public void onCancelled(FirebaseError firebaseError) {}
}
