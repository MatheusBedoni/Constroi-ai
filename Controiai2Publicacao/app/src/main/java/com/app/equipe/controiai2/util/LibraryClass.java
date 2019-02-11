package com.app.equipe.controiai2.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Created by matheus on 06/06/16.
 */
public class LibraryClass {
    public static String PREF = "com.app.matheus.controiai2.PREF";
    private static Firebase firebase;


    private LibraryClass(){}

    public static Firebase getFirebase(){
        if( firebase == null ){
            firebase = new Firebase("https://constroiai-app.firebaseio.com/");
        }

        return( firebase );
    }
    public static String getStorage(){

        return( "gs://constroiai-app.appspot.com" );
    }

    static public void saveSP(Context context, String key, String value ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    static public String getSP(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return( token );
    }
}
