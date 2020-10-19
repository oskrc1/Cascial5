package com.petagram.Catsocial.fragment;

import com.petagram.Catsocial.pojo.Mascota;
import com.petagram.Catsocial.adapter.MascotaAdaptador;

import java.util.ArrayList;

public interface iRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
