package com.app.equipe.controiai2;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.equipe.controiai2.auxiliares.UtilTCM;
import com.app.equipe.controiai2.domain.Profissional;
import com.app.equipe.controiai2.util.LibraryClass;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.storage.FirebaseStorage;

import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.Calendar;


public class ProfissionalPerfil extends AppCompatActivity implements ValueEventListener,Firebase.CompletionListener, RewardedVideoAdListener ,NavigationView.OnNavigationItemSelectedListener {
    private Profissional profissional;
    private FirebaseStorage storage;
    private Firebase firebase;
    private CoordinatorLayout coordinatorLayout;
    //
    private Toolbar mToolbar;
    private TextView profissaotext;
    private TextView nome;
    private TextView descricao;
    private TextView phone;
    private TextView profissao;
    private TextView textView ;
    private TextView destaque;
    private ScrollView scrollView;
    private RelativeLayout relative;
    private RewardedVideoAd mAd;
    String id = "ca-app-pub-2383249025819861/5336278044";
    private String userId;
    private boolean alarmeAtivo;


    private InkPageIndicator inkPageIndicator ;
    private ViewPager vp;

    String idAdmob="ca-app-pub-2383249025819861/5417935456";
    private InterstitialAd mInterstitialAd;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.profissional_perfil);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.corbranca));
        setSupportActionBar(mToolbar);


        vp = (ViewPager)findViewById(R.id.pager);

        inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);

        MobileAds.initialize(this, "ca-app-pub-2383249025819861~6210420170");
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        profissaotext = (TextView)findViewById(R.id.profissaoDef);


        MobileAds.initialize(this, "ca-app-pub-2383249025819861~6210420170");
        mAd = MobileAds.getRewardedVideoAdInstance(this);
        mAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        scrollView = (ScrollView) findViewById(R.id.scrollView5);
        relative = (RelativeLayout)findViewById(R.id.relative);

        nome = (TextView)findViewById(R.id.nome);

        descricao = (TextView) findViewById(R.id.descricao);
        phone = (TextView) findViewById(R.id.phone);

        profissao = (TextView)findViewById(R.id.profissao);

      //  nomeUsuario2 = (TextView)findViewById(R.id.nome_do_usuario);

        textView = (TextView)findViewById(R.id.texto_descricao);


        profissional = new Profissional();
        firebase = LibraryClass.getFirebase();
        storage = FirebaseStorage.getInstance();



    }

    private void loadRewardedVideoAd() {
        mAd.loadAd(id, new AdRequest.Builder().build());
    }
    //
    @Override
    protected void onResume() {
        super.onResume();
        //

        loadRewardedVideoAd();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                userId =  params.getString("userId");;
                profissional.setId(userId);

            }
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(idAdmob);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

       //Service
        alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);
        if(alarmeAtivo){
            intent = new Intent("ALARME_DISPARADO");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
            //
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 3);
            //
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, p);
        }
        //Profissional
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getParcelable("profissional") != null) {
            if (UtilTCM.verifyConnection(this)) {
                profissional = getIntent().getExtras().getParcelable("profissional");
                init();
            }else{
                aux();
            }

        } else {
            if (UtilTCM.verifyConnection(this)) {
                profissional.contextDataDB(this);
            }else{
                aux();
            }
        }

    }
    public void destaque(View view){


    }

    //
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        try {
            Profissional u = dataSnapshot.getValue(Profissional.class);
            profissional = u;
            init();
            //
            //
            if(profissional.getEmail().equals("ma@gmail.com")){
                Intent intent = new Intent(getBaseContext(), ProfissionalChamadas.class);

                startActivity(intent);
            }
            if(u.getVotoComentario() != u.getNovoVotoComentario() && !u.getNome().equals(" ")){

            }else if(u.getProfissao().equals(" ")){

            }else if(u.getDescricao().equals(" ")){
                Intent intent = new Intent(getBaseContext(), EditarDados.class);
                intent.putExtra("profissional",profissional);
                startActivity(intent);
            }else if(u.getQtdFotos() == 0) {
                Intent intent = new Intent(getBaseContext(), editarFotos.class);
                intent.putExtra("profissional", profissional);
                startActivity(intent);
            }else{
                scrollView.setVisibility(View.VISIBLE);
                relative.setVisibility(View.GONE);
            }
        }catch (NullPointerException e){}
    }
    //
    public void init(){
        try{



            scrollView.setVisibility(View.VISIBLE);
            relative.setVisibility(View.GONE);
            nome.setText(profissional.getNome());
            profissao.setText(profissional.getProfissao());
            if(profissional.getDescricao().equals(" ")){
                descricao.setText("Crie sua descricao é muito importante.");
            }else{
                descricao.setText(profissional.getDescricao());
            }
//            if(profissional.getVip() > 0){
//                linearLayout.setVisibility(View.GONE);
//            }
            phone.setText(profissional.getTel());


                vp.setAdapter(new AdapterImg(this, profissional));
                vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int arg0) {
                    }
                    @Override
                    public void onPageScrolled(int arg0, float arg1, int arg2) {
                    }
                    @Override
                    public void onPageScrollStateChanged(int arg0) {
                    }
                });
                inkPageIndicator.setViewPager(vp);



    }catch (Exception e){

    }
    if(profissional.getProfissao().equals("Construtora")){
        profissaotext.setText("Site: ");
        profissao.setText(profissional.getSite());
    }

    }
    //
    public void aux(){
        textView.setText("Sem internet :(");
        relative.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_termos){
            Uri uri = Uri.parse("http://appconstroiai.wixsite.com/appdonorte/termos-de-uso");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        if(id == R.id.action_dicas){
            Uri uri = Uri.parse("http://appconstroiai.wixsite.com/appdonorte/dicas");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        if(id == R.id.action_sair){
            firebase.unauth();
            Intent intent = new Intent(getBaseContext(),Entrar.class);
            startActivity(intent);
            finish();
        }
        if(id == R.id.action_configuracoes){

        }
        if(id == R.id.action_fale){
            Intent intent = new Intent(getBaseContext(),FaleConoscoProf.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //
    protected void showToast( String message ){
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }
    //
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if(firebaseError != null){
        }else{
        }
    }

    public void chamadas(View view){
        try {
            if (mAd.isLoaded()) {
                mAd.show();
            }
            Intent intent = new Intent(getBaseContext(), ProfissionalChamadas.class);
            startActivity(intent);
            profissional.setChamadas_velhas(profissional.getChamadas_novas());
            atualizar();
        }catch (Exception e){

        }
    }
    public void camera(View view){
        if (mAd.isLoaded()) {
            mAd.show();
        }

        Intent intent = new Intent(getBaseContext(), editarFotos.class);
        intent.putExtra("profissional",profissional);
        startActivity(intent);

    }
    public void favoritos(View view){
        if (mAd.isLoaded()) {
            mAd.show();
        }


    }
    public void avaliacoes(View view){
        if (mAd.isLoaded()) {
            mAd.show();
        }

        final Bundle params = new Bundle();
        params.putString("profissao", profissional.getId());
        params.putInt("coment",profissional.getQtd_usuarios());

    }





    //
    public void atualizar(){

        profissional.updateTodosProfDB(this);
    }
    //
    @Override
    public void onCancelled(FirebaseError firebaseError) {}
    //
    public void editar(View view) {
        try {
            if (mAd.isLoaded()) {
                mAd.show();
            }
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            Intent intent = new Intent(getBaseContext(), EditarDados.class);
            intent.putExtra("profissional", profissional);
            startActivity(intent);
        }catch (Exception e){

        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        return true;
    }
}
