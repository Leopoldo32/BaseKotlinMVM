package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.gallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rhonaldelgado.baseandroidkotlin.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        val lista: ListView = root.findViewById(R.id.lista)
        textView.text = getString(R.string.galleryfragment)


        val producto = Producto("Samsung Galaxy s20", 100.0, "Camara Ultimo modelo", "Samsung", R.drawable.celular1)
        val producto2 = Producto("Iphone 11 Pro", 92.0, "Celular xiaomi", "Iphone", R.drawable.celular2)
        val producto3 = Producto("Xiaomi Note 10 Pro", 3.0, "Ventilador electronico", "Xiaomi", R.drawable.celular3)

        val listaProductos = listOf(producto, producto2, producto3)

        val adapter = ProductosAdapter(mContext, listaProductos)
        lista.adapter = adapter


        lista.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(mContext, DetalleProducto::class.java)
            intent.putExtra("producto", listaProductos[i])
            startActivity(intent)
        }
        /*
        NO BORRAR: si utilizamos este codigo, traerá información del modelo, en este caso it= "Mensaje enviado desde el modelo Gallery"
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
}
