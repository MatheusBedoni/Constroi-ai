package com.app.equipe.controiai2;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import me.drakeet.materialdialog.MaterialDialog;

public class OrcamentoCompleto extends AppCompatActivity {

    private MaterialDialog mMaterialDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento_completo);



    }

    public void telefone(){
        mMaterialDialog = new MaterialDialog(this)
                .setTitle("Telefone: 99111-9158")
                .setMessage("Por favor diga que encontrou através do Constrói Aí?")
                .setPositiveButton("WhatsApp", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();


                        Intent sendIntent = new Intent("android.intent.action.MAIN");
                        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("55959111-9158") + "@s.whatsapp.net");//phone number without "+" prefix
                        startActivity(sendIntent);



                    }
                })
                .setNegativeButton("Telefonar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                        try{
                            Intent intent = null;

                            intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:99111-9158"));
                            startActivity(intent);



                        }catch (Exception e){

                        }
                    }
                });

        mMaterialDialog.show();
    }


    public void proximo(View view){
        Intent intent = new Intent(getBaseContext(),ProfissionalCategorias3.class);
        startActivity(intent);
    }

}
