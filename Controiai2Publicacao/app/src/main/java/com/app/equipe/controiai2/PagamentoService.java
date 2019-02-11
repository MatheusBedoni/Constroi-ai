package com.app.equipe.controiai2;

import com.app.equipe.controiai2.domain.Credito;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Matt on 16/04/2018.
 */

public interface PagamentoService {

    @FormUrlEncoded
    @POST("WSPagamentoDireto.rule?sys=EVS")
    Call<Credito> enviardados(@Field("id_autenticacao") int id_autenticacao,
                              @Field("token") String token,
                              @Field("Registros") ArrayList<String> creditoJson);
}
