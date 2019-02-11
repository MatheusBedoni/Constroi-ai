package com.app.equipe.controiai2.auxiliares;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by matheus on 09/03/16.
 */
public class UsuarioDes  implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement usuario= json.getAsJsonObject();
        if( json.getAsJsonObject().get("user") != null ){
            usuario = json.getAsJsonObject().get("user");
        }
      //  return ( new Gson().fromJson(usuario,Usuario.class));
        return null;
    }
}
