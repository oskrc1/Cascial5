package com.petagram.Catsocial.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petagram.Catsocial.restApi.deserializador.MascotaDeserializador;
import com.petagram.Catsocial.restApi.model.ConstantesRestApi;
import com.petagram.Catsocial.restApi.model.EndPointApi;
import com.petagram.Catsocial.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public EndPointApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointApi.class);
    }

    public Gson construirGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();
    }
}
