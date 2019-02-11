package com.app.equipe.controiai2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.equipe.controiai2.domain.Profissional;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by matheus on 24/06/16.
 */
public class ProfissionalFirebaseAdapter extends FirebaseRecyclerAdapter<Profissional,ProfissionalViewHolder> {


    public ProfissionalFirebaseAdapter(Class<Profissional> modelClass, int modelLayout, Class<ProfissionalViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ProfissionalViewHolder profissionalViewHolder, Profissional profissional, int i) {

    }


}
