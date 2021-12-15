package com.example.tiresportapp.Controlador;

import com.example.tiresportapp.Modelo.Producto;
import com.example.tiresportapp.R;

import java.util.ArrayList;

public class CargaProductos {
    public static ArrayList<Producto> cargaInicial= Llantas();

    public static ArrayList<Producto> Llantas(){

        ArrayList<Producto> listaProductos = new ArrayList<>();

        listaProductos.add(new Producto("LLanta goodyear R13",250000,100,0,0, R.drawable.wheel_blanco));
        listaProductos.add(new Producto("LLanta Michelin R14",250000,100,0,0,R.drawable._9765));
        listaProductos.add(new Producto("LLanta goodyear R15",250000,100,0,0,R.drawable.llanta_r15));

        return listaProductos;
    }
}
