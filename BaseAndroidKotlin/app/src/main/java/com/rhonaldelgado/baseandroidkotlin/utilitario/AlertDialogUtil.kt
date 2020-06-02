import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

/** COMO USAR EL UTILITARIO POR RHONAL A. DELGADO PADILLA 28 MAYO 2020
 * 1. PARA inicializar el utilitario copiar el siguiente codigo, justo dentro de la clase de la actividad donde vas a usar la alerta:  lateinit var alertDialogUtil: AlertDialogUtil
 * 2. DENTRO DE LA FUNCION override fun onCreate(savedInstanceState: Bundle?), colocar el siguiente codigo: alertDialogUtil = AlertDialogUtil(this)
 * 3. APARTIR DE ESTE PUNTO YA S PUEDE UTILIZAR EL UTILITARIO, PARA REALIZAR EL LLAMADO DE ALGUNA DE LAS FUNCIONES DENTRO DEL UTILITARIO,
 * FAVOR REVISAR LOS EJEMPLO QUE SE ENCUENTRAN COMENTADOS EN ESTE ARCHIVO.
 * 4. PARA VER LAS RESPUESTA DE LOS BOTONES, EN LA ACTIVIDAD DONDE LLAMAS LA ALERTA, ES NECESARIO AGREGAR LO SIGUIENTE EN LA CLASE DE LA ACTIVIDAD: "OnDialogTwoButtonClickListener" ó "OnDialogOneButtonClickListener"
 *    class LoginActivity : AppCompatActivity(), OnDialogTwoButtonClickListener {
 *    AL AGREGAR ESTE PARAMETRO, TE SALDRÁ UN ERROR EN LA CLASE, LE DAS ALT + ENTER Y SELECCIONAS LA OPCION "IMPLEMENTAR MIEMBROS"
 * */
class AlertDialogUtil(context: Context) {
    private val mContext: Context = context

    /**
     * Retorna un alert dialog Personalizado
     *
     * @param message  mensaje que se va a mostrar en la alerta, NO ES OBLIGATORIO
     * @param title el nombre que va a tener el titulo de la alerta, NO ES OBLIGATORIO
     * @param BtnText el nombre que va a tener el boton 1 de la alerta, NO ES OBLIGATORIO
     * @param Imaicon es la imagen que va aparecer al lado del titulo, no es obligatorio, NO ES OBLIGATORIO
     * @param Coloricon es el color del icono seleccionado anteriormente, no es obligatorio, NO ES OBLIGATORIO
     * @param cancelaBle valida si la alerta se puede cancelar, ES OBLIGATORIO
     * @param listener oyente para activar métodos de selección, SI ES OBLIGATORIO
     * @param
     */

    /* 1. EJEMPLO:  ASI SE HACE EL LLAMADO DE LA FUNCION DESDE CUALQUIER ACTIVITY

    alertDialogUtil.openAlertDialogOneButton(
                mContext,
                "Error", //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_message_default), //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_button1_default), //NO ES OBLIGATORIO
                android.R.drawable.ic_dialog_info, //NO ES OBLIGATORIO
                android.R.color.holo_blue_dark, //NO ES OBLIGATORIO
                true, // ES OBLIGATORIO
                this) // ES OBLIGATORIO
     */

    fun openAlertDialogOneButton(context: Context, title: String?, message: String?, BtnText: String?, Imaicon: Int?, Coloricon: Int?, cancelaBle: Boolean, listener: OnDialogOneButtonClickListener) {

        val builder = androidx.appcompat.app.AlertDialog.Builder(context)
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view =
            layoutInflater.inflate(com.rhonaldelgado.baseandroidkotlin.R.layout.dialog_p1, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.setCancelable(cancelaBle)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val unicoboton = view.findViewById<Button>(com.rhonaldelgado.baseandroidkotlin.R.id.btnVale)
        val titulo = view.findViewById<TextView>(com.rhonaldelgado.baseandroidkotlin.R.id.title)
        val info = view.findViewById<TextView>(com.rhonaldelgado.baseandroidkotlin.R.id.info)
        val imagenicon = view.findViewById<ImageView>(com.rhonaldelgado.baseandroidkotlin.R.id.ImaIcon)

        unicoboton.text = BtnText ?: context.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_button1_default)
        titulo.text = title ?: context.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_title_default)
        info.text = message ?: ""

        val colorBackground = Coloricon ?: com.rhonaldelgado.baseandroidkotlin.R.color.primaryGreen
        val col = AppCompatResources.getColorStateList(mContext, colorBackground)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            unicoboton.backgroundTintList = col
        }

        if (Imaicon != null) {
            val imageBackground =  AppCompatResources.getDrawable(mContext, Imaicon)
            imagenicon.visibility = View.VISIBLE
            imagenicon.setImageDrawable(imageBackground)
            ImageViewCompat.setImageTintList(imagenicon, col)
        } else {
            imagenicon.visibility = View.GONE
        }

        unicoboton.setOnClickListener {
            dialog.dismiss()
            listener.onOneButtonClicked()
        }
        dialog.show()
    }

    /**
     * Retorna un alert dialog Personalizado
     *
     * @param message  mensaje que se va a mostrar en la alerta, NO ES OBLIGATORIO
     * @param title el nombre que va a tener el titulo de la alerta, NO ES OBLIGATORIO
     * @param firstBtnText el nombre que va a tener el boton 1 de la alerta, NO ES OBLIGATORIO
     * @param secondBtnText el nombre que va a tener el boton 2 de la alerta, NO ES OBLIGATORIO
     * @param Imaicon es la imagen que va aparecer al lado del titulo, no es obligatorio, NO ES OBLIGATORIO
     * @param Coloricon es el color del icono seleccionado anteriormente, no es obligatorio, NO ES OBLIGATORIO
     * @param cancelaBle valida si la alerta se puede cancelar, ES OBLIGATORIO
     * @param listener oyente para activar métodos de selección, SI ES OBLIGATORIO
     * @param
     */

    /*  2. EJEMPLO:  ASI SE HACE EL LLAMADO DE LA FUNCION DESDE CUALQUIER ACTIVITY
        alertDialogUtil.openAlertDialogTwoButtons(
                mContext,
                null, //NO ES OBLIGATORIO
                mContext.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_message_default), //NO ES OBLIGATORIO
                null, //NO ES OBLIGATORIO
                null, //NO ES OBLIGATORIO
                android.R.drawable.ic_dialog_alert, // escoger el icono a mostrar //NO ES OBLIGATORIO
                android.R.color.holo_red_light, // escoger el color del icono //NO ES OBLIGATORIO
                false, // ES OBLIGATORIO
                this) // ES OBLIGATORIO
                */

    fun openAlertDialogTwoButtons(context: Context, title: String?, message: String?, firstBtnText: String?, secondBtnText: String?, Imaicon: Int?, Coloricon: Int?, cancelaBle: Boolean, listener: OnDialogTwoButtonClickListener) {

        val builder = androidx.appcompat.app.AlertDialog.Builder(context)
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(com.rhonaldelgado.baseandroidkotlin.R.layout.dialog_p2, null)
        builder.setView(view)

        val dialog = builder.create()
        dialog.setCancelable(cancelaBle)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val primerboton = view.findViewById<Button>(com.rhonaldelgado.baseandroidkotlin.R.id.btnSi)
        val segundoboton = view.findViewById<Button>(com.rhonaldelgado.baseandroidkotlin.R.id.btnNo)
        val titulo = view.findViewById<TextView>(com.rhonaldelgado.baseandroidkotlin.R.id.title)
        val info = view.findViewById<TextView>(com.rhonaldelgado.baseandroidkotlin.R.id.info)
        val imagenicon = view.findViewById<ImageView>(com.rhonaldelgado.baseandroidkotlin.R.id.ImaIcon)
        val view1 = view.findViewById<View>(com.rhonaldelgado.baseandroidkotlin.R.id.View1)
        val view2 = view.findViewById<View>(com.rhonaldelgado.baseandroidkotlin.R.id.View2)

        primerboton.text = firstBtnText ?: context.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_button1_default)
        segundoboton.text = secondBtnText ?: context.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_button2_default)
        titulo.text = title ?: context.resources.getString(com.rhonaldelgado.baseandroidkotlin.R.string.dialog_title_default)
        info.text = message ?: ""

        val colorBackground = Coloricon ?: com.rhonaldelgado.baseandroidkotlin.R.color.primaryGreen

        val col = AppCompatResources.getColorStateList(mContext, colorBackground)
        val colorValue = ContextCompat.getColor(context, colorBackground)
        view1.setBackgroundColor(colorValue)
        view2.setBackgroundColor(colorValue)
        primerboton.setTextColor(col)

        if (Imaicon != null) {
            val imageBackground =  AppCompatResources.getDrawable(mContext, Imaicon)
            imagenicon.visibility = View.VISIBLE
            imagenicon.setImageDrawable(imageBackground)
            ImageViewCompat.setImageTintList(imagenicon, col)
        } else {
            imagenicon.visibility = View.GONE
        }

        primerboton.setOnClickListener {
            dialog.dismiss()
            listener.onPositiveButtonClicked()
        }

        segundoboton.setOnClickListener {
            dialog.dismiss()
            listener.onNegativeButtonClicked()
        }
        dialog.show()
    }
}

interface OnDialogTwoButtonClickListener {
    fun onPositiveButtonClicked()
    fun onNegativeButtonClicked()
}

interface OnDialogOneButtonClickListener {
    fun onOneButtonClicked()
}