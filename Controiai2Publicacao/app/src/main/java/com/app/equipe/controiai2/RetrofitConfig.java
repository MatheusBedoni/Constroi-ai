package com.app.equipe.controiai2;



import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Matt on 16/04/2018.
 */

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://app.ticketphone.com.br/webrun/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public PagamentoService getPagamentoService() {
        return this.retrofit.create(PagamentoService.class);
    }
}
