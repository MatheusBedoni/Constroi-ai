package com.app.equipe.controiai2.adapters;

import com.app.equipe.controiai2.domain.Chamadas;
import com.app.equipe.controiai2.interfaces.RecyclerViewOnClickListenerHack;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by matheus on 27/09/16.
 */
public abstract class ChamadasFirebaseAdapter extends FirebaseRecyclerAdapter<Chamadas,ChamadasViewHolder> implements RecyclerViewOnClickListenerHack {


    public ChamadasFirebaseAdapter(Class<Chamadas> modelClass, int modelLayout, Class<ChamadasViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    protected abstract void populateViewHolder(ChamadasViewHolder useViewHolder, Chamadas chamada, int i);
}