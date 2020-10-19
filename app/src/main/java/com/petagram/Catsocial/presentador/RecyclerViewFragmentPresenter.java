package com.petagram.Catsocial.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.petagram.Catsocial.db.ConstructorMascotas;
import com.petagram.Catsocial.fragment.iRecyclerViewFragmentView;
import com.petagram.Catsocial.pojo.Mascota;
import com.petagram.Catsocial.restApi.adapter.RestApiAdapter;
import com.petagram.Catsocial.restApi.model.EndPointApi;
import com.petagram.Catsocial.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements iRecyclerViewFragmentPresenter{

    private com.petagram.Catsocial.fragment.iRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(iRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){

        this.iRecyclerViewFragmentView=iRecyclerViewFragmentView;
        this.context = context;
        //obtenerMascotasBD();
        obtenerMascotasRetrofit();
    }

    @Override
    public void obtenerMascotasBD() {

        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void obtenerMascotasRetrofit() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMedia = restApiAdapter.construirGsonDeserializadorMediaRecent();
        EndPointApi endPointAdapter = restApiAdapter.establecerConexionRestApiInstagram(gsonMedia);
        Call<MascotaResponse> contactoResponseCall = endPointAdapter.getRecentMedia();

        contactoResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexion", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }
}
