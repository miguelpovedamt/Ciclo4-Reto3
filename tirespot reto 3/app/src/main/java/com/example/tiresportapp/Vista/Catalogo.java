package com.example.tiresportapp.Vista;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiresportapp.Controlador.AdaptadorProductos;
import com.example.tiresportapp.Modelo.Llanta;
import com.example.tiresportapp.Casodeuso.LlantaActionCase;
import com.example.tiresportapp.Controlador.MyOpenHelper;
import com.example.tiresportapp.R;

import java.util.ArrayList;


//metodo principal de nuestra actividad
public class Catalogo extends AppCompatActivity {

    //declara un boton para comprar
    private Button btnComprar1;

    //declara un boton para comprar
    private Button btnComprar2;

    //declara un boton para buscar
    private Button btnBuscar;

    //declara un visor de texto
    private TextView txtLLanta;

    private ConstraintLayout padre;

    private Llanta llanta;

    ArrayList<Llanta> listaLlantas;

    ArrayList<Llanta> listaCarrito;

    private RecyclerView rcvProd;



    @Override
    //sobrescribe el metodo cuando se crea la actividad
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        //declara una barra de accion
        ActionBar barraMenu = getSupportActionBar();

        //muestra la barra de acciones en la pantalla
        barraMenu.setDisplayShowHomeEnabled(true);

        //determina un logo previamente guardado
        barraMenu.setLogo(R.mipmap.ic_launcher);

        //muestra un titulo en la barra de acciones
        barraMenu.setTitle("Productos");

        //muestra un subtitulo en la barra de acciones
        barraMenu.setSubtitle("Elige un producto");

        //muestra un logo previamente determinado
        barraMenu.setDisplayUseLogoEnabled(true);

        //cambiar el color de la barra para que siempre sea el mismo
        barraMenu.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#313d8c")));

        MyOpenHelper dataBase = new MyOpenHelper(Catalogo.this);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        listaLlantas = LlantaActionCase.consultarProductos(this);

        listaCarrito = new ArrayList<>();

        int azulPrincipal = getResources().getColor(R.color.azulPrincipal);
        int naranjaPrincipal = getResources().getColor(R.color.naranjaPrincipal);
        int blanco = getResources().getColor(R.color.white);

        rcvProd = (RecyclerView) findViewById(R.id.rcvProductos);
        rcvProd.setHasFixedSize(true);
        rcvProd.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AdaptadorProductos adaptador = new AdaptadorProductos(listaLlantas,this);
        rcvProd.setAdapter(adaptador);

    }


    @Override
    //sobrescribe el metodo de creacion de menu
    public boolean onCreateOptionsMenu(Menu menuPrincipal) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimnu,menuPrincipal);
        MenuItem productos = menuPrincipal.findItem(R.id.botonProductos);
        productos.setVisible(false);
        return true;
    }
    @Override
    //determina que accion se va a ejecutar dependiendo de eleccion en el menu
    public boolean onOptionsItemSelected (MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.botonProductos:
                Intent principal = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(principal);
                return true;

            case R.id.botonServicios:
                Intent servicios = new Intent(getApplicationContext(), com.example.tiresportapp.Vista.servicios.class);
                startActivity(servicios);
                return true;

            case R.id.botonSucursales:
                Intent sucursales = new Intent(getApplicationContext(), com.example.tiresportapp.Vista.sucursales.class);
                startActivity(sucursales);
                return true;

            case R.id.carrito:

                Intent intent = new Intent(this, Carrito.class);
                startActivity(intent);

            default:
                return true;

        }
    }
}
