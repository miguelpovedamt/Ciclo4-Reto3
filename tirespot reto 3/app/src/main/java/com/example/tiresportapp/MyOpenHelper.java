package com.example.tiresportapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String  lLANTAS_TABLE_CREATE = "CREATE TABLE llantas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio INTEGER, inventario INTEGER, cantidad INTEGER, imagen INTEGER)";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "autopartes.db";

    public MyOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        crearTablaLlantas(db);
        ArrayList<Llanta> llantas = CargaLlantas.Llantas();

        for (Llanta l : llantas
             ) {
            insertarLlanta(l,db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS llantas");
        onCreate(db);
    }
    public void crearTablaLlantas (SQLiteDatabase db){
        db.execSQL(lLANTAS_TABLE_CREATE);
        //db.execSQL("DROP TABLE IF EXISTS LLANTAS");
    }
    public void insertarLlanta(Llanta l,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nombre",l.getNombre());
        cv.put("precio",l.getPrecio());
        cv.put("inventario",l.getInventario());
        cv.put("cantidad",l.getCantidad());
        cv.put("imagen",l.getImagen());
        db.insert("llantas",null,cv);
    }
    public void borrarLlanta(int id, SQLiteDatabase db){
        String[] args = new String[]{""+id};
        db.delete("llantas", "id=?",args);
    }
    public Cursor leerTodo(SQLiteDatabase db){
        return db.query("llantas", null,null,null,null,null,null);
    }
    public void actualizarLlanta(Llanta l,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("nombre",l.getNombre());
        cv.put("precio",l.getPrecio());
        cv.put("inventario",l.getInventario());
        cv.put("cantidad",l.getCantidad());
        cv.put("imagen",l.getImagen());
        String[] args = new String[]{""+l.getId()};
        db.update("llantas",cv,"id=?",args);
    }

    public  void cambiarCantidad(Integer id,Integer cantidad,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put("cantidad",cantidad);
        String[] arg = new String[]{""+id};
        db.update("llantas", cv, "id=?", arg);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
