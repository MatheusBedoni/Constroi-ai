package com.app.equipe.controiai2.auxiliares;

import com.app.equipe.controiai2.domain.Profissional;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by matheus on 05/03/16.
 */
public class ProfissionalDes  implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement profissional= json.getAsJsonObject();
        if( json.getAsJsonObject().get("pro") != null ){
            profissional = json.getAsJsonObject().get("pro");
        }
        return ( new Gson().fromJson(profissional,Profissional.class));
    }
}
