package com.app.equipe.controiai2;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.app.equipe.controiai2.domain.Profissional;

import com.firebase.client.Firebase;

import com.google.firebase.storage.FirebaseStorage;

/**
 * Created by matheus on 09/08/16.
 */
public class AdapterImg extends PagerAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    //
    private Profissional profissional;
    private FirebaseStorage storage;
    //
    public AdapterImg(Context context,  Profissional profissional){
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //
        this.profissional = profissional;
        storage = FirebaseStorage.getInstance();
    }
    //
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return profissional.getQtdFotos();
    }
    //
    @Override
    public boolean isViewFromObject(View view, Object obj) {
        // TODO Auto-generated method stub
        // Log.i("Script", "view == obj: "+((view == obj) ? "1" : "0"));
        // Log.i("Script", "view == ((TextView) obj).getParent(): "+((view == ((TextView) obj).getParent()) ? "1" : "0"));
        // return view == ((TextView) obj).getParent();
        return view == ((LinearLayout) obj);
    }
    //
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View itemView = mLayoutInflater.inflate(R.layout.view_pager, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.placeholde);
        //
        if(position == 0){
            Glide
                    .with(mContext)
                    .load(profissional.getPrimeiro())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .placeholder(R.drawable.placeholde)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        if(position == 1){
            Glide
                    .with(mContext)
                    .load(profissional.getSegunda())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .placeholder(R.drawable.placeholde)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        if(position == 2){
            Glide
                    .with(mContext)
                    .load(profissional.getTerceira())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .placeholder(R.drawable.placeholde)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        if(position == 3){
            Glide
                    .with(mContext)
                    .load(profissional.getQuarta())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .placeholder(R.drawable.placeholde)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imageView);
        }
        //
        container.addView(itemView);
        return itemView;
    }
    //
    @Override
    public void destroyItem(ViewGroup container, int position, Object view){
        //Log.i("Script", "Destroy: Carro: "+(position + 1));
        container.removeView((LinearLayout) view);
    }
}
