package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.alert

import AlertDialogUtil
import OnDialogOneButtonClickListener
import OnDialogTwoButtonClickListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rhonaldelgado.baseandroidkotlin.R


class AlertFragment : Fragment(), OnDialogTwoButtonClickListener, OnDialogOneButtonClickListener {

    private lateinit var mContext: Context
    private lateinit var slideshowViewModel: AlertViewModel
    lateinit var alertDialogUtil: AlertDialogUtil

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel = ViewModelProviders.of(this).get(AlertViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        alertDialogUtil = AlertDialogUtil(mContext)

        val textView: TextView = root.findViewById(R.id.text_slideshow)
        val alerta1: Button = root.findViewById(R.id.Alerta1)
        val alerta2: Button = root.findViewById(R.id.Alerta2)
        textView.text = getString(R.string.sliderfragment)



        alerta1.setOnClickListener {
            alertDialogUtil.openAlertDialogTwoButtons(
                mContext,
                null, //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_message_default), //NO ES OBLIGATORIO
                null, //NO ES OBLIGATORIO
                null, //NO ES OBLIGATORIO
                android.R.drawable.ic_dialog_alert, // escoger el icono a mostrar //NO ES OBLIGATORIO
                android.R.color.holo_red_light, // escoger el color del icono //NO ES OBLIGATORIO
                false, // ES OBLIGATORIO
                this
            ) // ES OBLIGATORIO
        }

        alerta2.setOnClickListener {
            alertDialogUtil.openAlertDialogOneButton(
                mContext,
                "Error", //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_message_default), //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_button1_default), //NO ES OBLIGATORIO
                android.R.drawable.ic_dialog_info, //NO ES OBLIGATORIO
                android.R.color.holo_blue_dark, //NO ES OBLIGATORIO
                true, // ES OBLIGATORIO
                this
            ) // ES OBLIGATORIO
        }
        /*
        NO BORRAR: si utilizamos este codigo, traerá información del modelo, en este caso it= "Mensaje enviado desde el modelo Slides"
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onPositiveButtonClicked() {
        Toast.makeText(activity, "Boton 1", Toast.LENGTH_LONG).show()
    }

    override fun onNegativeButtonClicked() {
        Toast.makeText(activity, "Boton 2", Toast.LENGTH_LONG).show()
    }

    override fun onOneButtonClicked() {
        Toast.makeText(activity, "Unico Boton", Toast.LENGTH_LONG).show()
    }
}
