package com.example.tiresportapp.Controlador;

import com.example.tiresportapp.Modelo.Llanta;
import com.example.tiresportapp.R;

import java.util.ArrayList;

public class CargaLlantas {
    public static ArrayList<Llanta> cargaInicial= Llantas();

    public static ArrayList<Llanta> Llantas(){

        ArrayList<Llanta>  listaLlantas = new ArrayList<>();

        listaLlantas.add(new Llanta("LLanta goodyear R13",250000,100,0, R.drawable.wheel_blanco));
        listaLlantas.add(new Llanta("LLanta Michelin R14",250000,100,0,R.drawable._9765));
        listaLlantas.add(new Llanta("LLanta goodyear R15",250000,100,0,R.drawable.llanta_r15));

        return listaLlantas;
    }
}
