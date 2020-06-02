package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.gallery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import com.rhonaldelgado.baseandroidkotlin.R
import kotlinx.android.synthetic.main.activity_detalle_producto.*
import kotlinx.android.synthetic.main.content_detalle_producto.view.*

class DetalleProducto : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_producto)


        val titulo = findViewById<Toolbar>(R.id.toolbartitle)
        val imagenproducto = findViewById<ImageView>(R.id.imagenProducto)
        val viewLayoutItem = findViewById<View>(R.id.layoutDetailInfo)

        val producto = intent.getSerializableExtra("producto") as Producto

        titulo.title = producto.nombre
        viewLayoutItem.textoPrecio.text = producto.precio.toString()
        viewLayoutItem.textoMarca.text = producto.marca
        viewLayoutItem.textoDescripcion.text = producto.descripcion

        imagenproducto.setImageResource(producto.imagen)
        setSupportActionBar(titulo)

        //Habilita el icono del boton de atras
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnVolatil.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
    //Funcion que genera al dar clic sobre el boton atras
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
