package com.app.equipe.controiai2;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.app.equipe.controiai2.adapters.PrecisaAdapter;
import com.app.equipe.controiai2.domain.Precisa;

import com.app.equipe.controiai2.interfaces.RecyclerViewOnClickListenerHack;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

public class ProfissionalCategorias3 extends AppCompatActivity implements RecyclerViewOnClickListenerHack{
    private Toolbar mToolbar;
    private ListView lv,lvf;

    //
    private SliderLayout slImages;


    private LinearLayout linear;
    private MaterialDialog mMaterialDialog;

    private RecyclerView mRecyclerView;
    private List<Precisa> mList;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profissional_categorias);
        //
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("O que você precisa?");
        setSupportActionBar(mToolbar);

        linear = (LinearLayout)findViewById(R.id.linear);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mList =  getSetCarList(5);
        PrecisaAdapter adapter = new PrecisaAdapter(this, mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter( adapter );
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

    public List<Precisa> getSetCarList(int qtd) {
        String[] brands = new String[]{"Construção e reformas", " Pequenos reparos", "Serviços elétricos", "Serviços hidráulicos","Climatização"};
        int[] photos = new int[]{R.drawable.ddd, R.drawable.reform, R.drawable.eletricist, R.drawable.pequenosrep,R.drawable.climatizacao};
        List<Precisa> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Precisa c = new Precisa(brands[i % brands.length], photos[i % photos.length]);
            listAux.add(c);
        }
        return (listAux);
    }

    public void verificados( View view){
        Intent intent = new Intent(getBaseContext(),ProfVerificados.class);
        startActivity(intent);
    }

    @Override
    public void onClickListener(View view, int position) {
        if(position == 0){
            Intent intent = new Intent(getBaseContext(),PedidoOrcamentoReformaUm.class);
            startActivity(intent);
        }
        if(position == 1){
            Intent intent = new Intent(getBaseContext(),Pedidoorcamentoum.class);
            startActivity(intent);
        }
        if (position == 2) {
            Intent intent = new Intent(getBaseContext(),PedidoOrcamentoEletricistaum.class);
            startActivity(intent);
        }
        if(position == 3){
            Intent intent = new Intent(getBaseContext(),PedidoOrcamentoEncanadorUm.class);
            startActivity(intent);
        }
        if(position == 4){
            Intent intent = new Intent(getBaseContext(),PedidoOrcamentoClimatizacaoUm.class);
            startActivity(intent);
        }
    }
}
