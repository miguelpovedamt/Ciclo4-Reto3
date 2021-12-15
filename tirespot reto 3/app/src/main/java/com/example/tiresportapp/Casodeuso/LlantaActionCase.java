package com.example.tiresportapp.Casodeuso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiresportapp.Modelo.Llanta;
import com.example.tiresportapp.Controlador.MyOpenHelper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class LlantaActionCase {


    public static void setCantidad(Integer id,Integer cantidad,Context context){

        MyOpenHelper dataBase = new MyOpenHelper(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        try{
            dataBase.cambiarCantidad(id, cantidad, db);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public static void vaciarCarrito(Context context){
        MyOpenHelper dataBase = new MyOpenHelper(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        ArrayList<Llanta>llantas = consultarProductos(context);

        try{
            for (Llanta l: llantas
                 ) {
                dataBase.cambiarCantidad(l.getId(), 0, db);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public static ArrayList<Llanta> consultarProductos(Context context){
        ArrayList<Llanta> llantas = new ArrayList<>();

        MyOpenHelper dataBase = new MyOpenHelper(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();


        Cursor c = db.query("llantas", null,null,null,null,null,null);
        if (c != null){
            c.moveToFirst();

            do{
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = c.getString(c.getColumnIndex("nombre"));
                @SuppressLint("Range") int precio = c.getInt(c.getColumnIndex("precio"));
                @SuppressLint("Range") int inventario = c.getInt(c.getColumnIndex("inventario"));
                @SuppressLint("Range") int cantidad = c.getInt(c.getColumnIndex("cantidad"));
                @SuppressLint("Range") int imagen = c.getInt(c.getColumnIndex("imagen"));

                llantas.add(new Llanta(id,nombre,precio,inventario,cantidad,imagen));
            } while (c.moveToNext());
            c.close();
            db.close();
        }return llantas;
    }

    public static String formatearNumero(int numero){

        DecimalFormatSymbols punto = new DecimalFormatSymbols();
        punto.setGroupingSeparator('.');
        DecimalFormat formatoNumero= new DecimalFormat("###,###,###",punto);
        String numeroFormateado = "$"+formatoNumero.format(numero*1.0);

        return numeroFormateado;

    }

}
