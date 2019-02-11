package com.app.equipe.controiai2.auxiliares;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by matheus on 08/03/16.
 */
public class VerificaErrosDes implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement verificaErros= json.getAsJsonObject();
        if( json.getAsJsonObject().get("erro") != null ){
            verificaErros = json.getAsJsonObject().get("erro");
        }
        return ( new Gson().fromJson(verificaErros,VerificaErros.class));
    }

}
