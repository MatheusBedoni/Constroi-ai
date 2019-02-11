package com.app.equipe.controiai2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.app.equipe.controiai2.domain.Profissional;
import com.app.equipe.controiai2.util.LibraryClass;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.text.Line;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sangcomz.fishbun.FishBun;
import com.sangcomz.fishbun.define.Define;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import me.drakeet.materialdialog.MaterialDialog;

public class editarFotos extends AppCompatActivity implements Firebase.CompletionListener {
    //
    private Profissional profissional;
    private FirebaseStorage storage;
    private Firebase firebase;
    private GoogleApiClient client;
    private StorageReference storageRef;
    //
    private int i = 0;
    ArrayList<String> path = new ArrayList<>();
    private String ur1 = " ",ur2 = " ",ur3 = " ",ur4 = " ",tes1,tes2,tes3;
    //
    String idAdmob="ca-app-pub-2383249025819861/5417935456";

    private InterstitialAd mInterstitialAd;
    private ProgressDialog progress;
    private LinearLayout  rel2,rel3;
    private ScrollView rel1;
    private Toolbar toolbar;
    private Bitmap bitmap,bitmapdec;
    private TextView txt1,txt2,txt3;
    private TextView destaque;
    private int dataInt;
    private MaterialDialog mMaterialDialog;
    private ImageView foto1,foto2,foto3,foto4;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_fotos);
        //
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        toolbar.setTitle("Adicionar fotos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-2383249025819861/5417935456");
        //
        MobileAds.initialize(this, "ca-app-pub-2383249025819861~6210420170");

        firebase = LibraryClass.getFirebase();
        storage = FirebaseStorage.getInstance();
        profissional = new Profissional();
        storageRef = storage.getReferenceFromUrl(LibraryClass.getStorage());
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
        progress = new ProgressDialog(this);
        foto1 = (ImageView)findViewById(R.id.ivProfissaoFoto1) ;
        foto2 = (ImageView)findViewById(R.id.ivProfissaoFoto2);
        foto3 = (ImageView)findViewById(R.id.ivProfissaoFoto3);
        foto4 = (ImageView)findViewById(R.id.ivProfissaoFoto4);
        rel1 = (ScrollView) findViewById(R.id.rel1);
        rel2 = (LinearLayout) findViewById(R.id.rel2);
        rel3 = (LinearLayout)findViewById(R.id.rel3);
        txt1 = (TextView)findViewById(R.id.teste1);
        txt2 = (TextView)findViewById(R.id.teste2);
        txt3 = (TextView)findViewById(R.id.teste3);
        //
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        int i = 0;

        for(i = 0; i < profissional.getQtdFotos(); i++){
            if(i == 0){
                ur1 = profissional.getPrimeiro();
                Glide
                        .with(getBaseContext())
                        .load(ur1)
                        .thumbnail(0.5f)
                        .centerCrop()
                        .placeholder(R.drawable.placeholde)
                        .crossFade(50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(foto1);
            }
            if(i == 1){
                ur2 = profissional.getSegunda();
                Glide
                        .with(getBaseContext())
                        .load(ur2)
                        .thumbnail(0.5f)
                        .centerCrop()
                        .placeholder(R.drawable.placeholde)
                        .crossFade(50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(foto2);
            }
            if(i == 2){
                ur3 = profissional.getTerceira();
                Glide
                        .with(getBaseContext())
                        .load(ur3)
                        .thumbnail(0.5f)
                        .centerCrop()
                        .placeholder(R.drawable.placeholde)
                        .crossFade(50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(foto3);
            }
            if(i == 3) {
                ur4 = profissional.getQuarta();
                Glide
                        .with(getBaseContext())
                        .load(ur4)
                        .thumbnail(0.5f)
                        .centerCrop()
                        .placeholder(R.drawable.placeholde)
                        .crossFade(50)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(foto4);
            }
        }
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(idAdmob);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    //
    public void adicionarFotos() {

        if(profissional.getQtdFotos() < 4){
            FishBun.with(editarFotos.this)
                    .setPickerCount(1)
                    .setPickerSpanCount(3)
                    .setActionBarColor(Color.parseColor("#FF5722"), Color.parseColor("#E64A19"))
                    .textOnImagesSelectionLimitReached("Limit Reached!")
                    .textOnNothingSelected("Nothing Selected")
                    .setArrayPaths(path)
                    .setButtonInAlbumActiviy(true)
                    .setCamera(true)
                    .setReachLimitAutomaticClose(true)
                    .startAlbum();
        }else{
            showToast("Você já atingiu o limite de fotos enviadas");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_foto, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
//            if (mAd.isLoaded()) {
//                mAd.show();
//            } else {
//
//            }
            finish();

        }
        if(id == R.id.action_fale){
            adicionarFotos();
        }
        return true;
    }


    //
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageData) {
        super.onActivityResult(requestCode, resultCode, imageData);
        //
        switch (requestCode) {
            case Define.ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    path = imageData.getStringArrayListExtra(Define.INTENT_PATH);
                        int n = path.size();

                            for (i = 0; i < n; i++) {
                                rel2.setVisibility(View.VISIBLE);
                                toolbar.setTitle("Enviando");
                                rel1.setVisibility(View.GONE);
                                byte[] dataByte;
                                try{
                                    int rotacao = getRotationForImage(path.get(i));
                                    bitmap = BitmapFactory.decodeFile(path.get(i));
                                    bitmap = rotateBitmap(bitmap, rotacao);
                                    dataByte = BinaryBytes.getBitmapInBytes(bitmap);
                                    bitmapdec = decodeFile(dataByte);
                                }catch (Exception e){
                                    showToast("Aconteceu um erro ao tentar enviar");
                                    Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                                    startActivity(intent);
                                    finish();
                                }
                                //bitmap = resizeImage(this, bitmap, 200, 200);
                                dataByte = BinaryBytes.getBitmapInBytes(bitmapdec);
                                //String nome = BinaryBytes.getResourceName(this,R.drawable.camaro);
                                // Create a reference to "mountains.jpg"
                                StorageReference mountainsRef = storageRef.child(profissional.getId() + "/" + profissional.getNome() + profissional.getQtdFotos());
                                // Create a reference to 'images/mountains.jpg'
                                StorageReference mountainImagesRef = storageRef.child(profissional.getId() + "/" + profissional.getNome() + profissional.getQtdFotos());
                                // While the file names are the same, the references point to different files
                                mountainsRef.getName().equals(mountainImagesRef.getName());    // true
                                mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
                                // Get the data from an ImageView as bytes
                                UploadTask uploadTask = mountainsRef.putBytes(dataByte);
                                uploadTask.addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        showToast("Aconteceu um erro ao tentar enviar");
                                        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                                        if (profissional.getQtdFotos() == 0) {
                                            profissional.setQtdFotos(profissional.getQtdFotos() + 1);
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            profissional.setPrimeiro(downloadUrl.toString());
                                            profissional.setUrl(downloadUrl.toString());
                                            atualizar();
                                        } else if (profissional.getQtdFotos() == 1) {
                                            profissional.setQtdFotos(profissional.getQtdFotos() + 1);
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            profissional.setSegunda(downloadUrl.toString());
                                            atualizar();
                                        } else if (profissional.getQtdFotos() == 2) {
                                            profissional.setQtdFotos(profissional.getQtdFotos() + 1);
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            profissional.setTerceira(downloadUrl.toString());
                                            atualizar();
                                        } else if (profissional.getQtdFotos() == 3) {
                                            profissional.setQtdFotos(profissional.getQtdFotos() + 1);
                                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                            profissional.setQuarta(downloadUrl.toString());
                                            atualizar();
                                        }

                                        //
                                        showToast("Sua imagem foi enviada");
                                        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
                                        startActivity(intent);
                                        finish();

                                    }

                                });
                            }
                        break;

                }
        }
    }
    //
    private Bitmap decodeFile(byte[] f) {
        try {
            // Decodifica o tamanho da imagem
            int REQUIRED_SIZE = 0;
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            //BitmapFactory.decodeStream(new FileInputStream(f), null, o);
            BitmapFactory.decodeByteArray(f, 0, f.length, o);
            // O novo tamanho que queremos
            REQUIRED_SIZE = 200;
            // Achar o valor correto para a escala
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }
            // Decodifica com o inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeByteArray(f, 0, f.length, o2);
        } catch (Exception e) {
        }
        return null;
    }
    //
    private static Bitmap resizeImage(Context context, Bitmap bmpOriginal,
                                      float newWidth, float newHeight) {
        Bitmap novoBmp = null;
        //
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();
        //
        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newHeight * densityFactor;
        //Calcula escala em percentagem do tamanho original para o novo tamanho
        float scalaW = novoW / w;
        float scalaH = novoH / h;
        // Criando uma matrix para manipulação da imagem BitMap
        Matrix matrix = new Matrix();
        // Definindo a proporção da escala para o matrix
        matrix.postScale(scalaW, scalaH);
        //criando o novo BitMap com o novo tamanho
        novoBmp = Bitmap.createBitmap(bmpOriginal, 0, 0, w, h, matrix, true);
        return novoBmp;
    }
    //
    public void setFoto1(View view){
        if(ur1.equals(" ")){
        }else{
            mMaterialDialog = new MaterialDialog(this)
                    .setTitle("Remover foto")
                    .setMessage("Deseja remover essa foto?")
                    .setPositiveButton("SIM", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            if(profissional.getQtdFotos() == 1){
                                showToast("Você so tem essa foto, não será possivel excluir!");
                            }else{
                                //
                                if(ur1.equals(profissional.getUrl())){
                                    if(!ur2.equals(" ")){
                                        profissional.setUrl(ur2);
                                    }else{
                                        if(!ur3.equals(" ")){
                                            profissional.setUrl(ur3);
                                        }else{
                                            if(!ur4.equals(" ")){
                                                profissional.setUrl(ur4);
                                            }
                                        }
                                    }
                                }
                                //
                                profissional.setPrimeiro(ur2);
                                profissional.setSegunda(ur3);
                                profissional.setTerceira(ur4);
                                profissional.setQuarta(" ");
                                profissional.setQtdFotos(profissional.getQtdFotos() - 1);
                                //
                                atualizar();
                                showToast("Essa imagem foi excluida");
                                Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("NAO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }
    }

    public void setFoto2(View view){
        if(ur2.equals(" ")){
        }else{
            mMaterialDialog = new MaterialDialog(this)
                    .setTitle("Remover foto")
                    .setMessage("Deseja remover essa foto?")
                    .setPositiveButton("SIM", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            if(profissional.getQtdFotos() == 1){
                                showToast("Você so tem essa foto, não será possivel excluir!");
                            }else{
                                if(ur2.equals(profissional.getUrl())){
                                    if(!ur3.equals(" ")){
                                        profissional.setUrl(ur3);
                                    }else{
                                        if(!ur4.equals(" ")){
                                            profissional.setUrl(ur4);
                                        }else{
                                            if(!ur1.equals(" ")){
                                                profissional.setUrl(ur1);
                                            }else{
                                                profissional.setUrl(" ");
                                            }
                                        }
                                    }
                                }
                                //
                                profissional.setSegunda(ur3);
                                profissional.setTerceira(ur4);
                                profissional.setQuarta(" ");
                                profissional.setQtdFotos(profissional.getQtdFotos() - 1);
                                atualizar();
                                //
                                showToast("Essa imagem foi excluida");
                                Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("NAO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }
    }
    //
    public void setFoto3(View view){
        if(ur3.equals(" ")){
        }else{
            mMaterialDialog = new MaterialDialog(this)
                    .setTitle("Remover foto")
                    .setMessage("Deseja remover essa foto?")
                    .setPositiveButton("SIM", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            if(profissional.getQtdFotos() == 1){
                                showToast("Você so tem essa foto, não será possivel excluir!");
                            }else{
                                if(ur3.equals(profissional.getUrl())){
                                    if(!ur4.equals(" ")){
                                        profissional.setUrl(ur4);
                                    }else{
                                        if(!ur1.equals(" ")){
                                            profissional.setUrl(ur1);
                                        }else{
                                            if(!ur2.equals(" ")){
                                                profissional.setUrl(ur2);
                                            }else{
                                                profissional.setUrl(" ");
                                            }
                                        }
                                    }
                                }
                                //
                                profissional.setTerceira(ur4);
                                profissional.setQuarta(" ");
                                profissional.setQtdFotos(profissional.getQtdFotos() - 1);
                                atualizar();
                                //
                                showToast("Essa imagem foi excluida");
                                Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("NAO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }
    }
    //
    public void setFoto4(View view){
        if(ur4.equals(" ")){
        }else{
            mMaterialDialog = new MaterialDialog(this)
                    .setTitle("Remover foto")
                    .setMessage("Deseja remover essa foto?")
                    .setPositiveButton("SIM", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                            if(profissional.getQtdFotos() == 1){
                                showToast("Você so tem essa foto, não será possivel excluir!");
                            }else{
                                if(ur4.equals(profissional.getUrl())){
                                    if(!ur1.equals(" ")){
                                        profissional.setUrl(ur1);
                                    }else{
                                        if(!ur2.equals(" ")){
                                            profissional.setUrl(ur2);
                                        }else{
                                            if(!ur3.equals(" ")){
                                                profissional.setUrl(ur3);
                                            }else{
                                                profissional.setUrl(" ");
                                            }
                                        }
                                    }
                                }
                                profissional.setQuarta(" ");
                                profissional.setQtdFotos(profissional.getQtdFotos() - 1);
                                atualizar();
                                showToast("Essa imagem foi excluida");
                                Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    })
                    .setNegativeButton("NAO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }
    }
    //
    public void voltar(View view) {
        Intent intent = new Intent(getBaseContext(),ProfissionalPerfil.class);
        startActivity(intent);
    }
    //
    public void salvar(View view) {
        Intent intent = new Intent(getBaseContext(), ProfissionalPerfil.class);
        startActivity(intent);
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        try {
            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_NORMAL:
                    return bitmap;
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.setScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_TRANSPOSE:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_TRANSVERSE:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(-90);
                    break;
                default:
                    return bitmap;
            }
            try {
                Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return bmRotated;
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //
    public int getRotationForImage(String path) {
        int rotation = 0;
        try {
            ExifInterface exif = new ExifInterface(path);
            rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotation;
    }


    //
    public void atualizar() {
        //profissional.setData_entrada(dataInt);

        profissional.updateTodosProfDB(this);
    }

    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        if (firebaseError != null) {
            progress.dismiss();
        } else {
        }
    }
    //
    protected void showToast(String message) {
        Toast.makeText(this,
                message,
                Toast.LENGTH_LONG)
                .show();
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("editarFotos Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    //
    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }
    //
    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
