package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.home

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rhonaldelgado.baseandroidkotlin.R
import com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.menuprincipal
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


    private val PERMISSION_CODE: Int = 1000
    private val IMAGE_CAPTURE_CODE: Int = 1001
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mContext: Context
    var image_uri: Uri? = null
    lateinit var image_view : ImageView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        textView.text = getString(R.string.homefragment)
        val btncam = root.findViewById<Button>(R.id.btnCam)
        image_view = root.findViewById(R.id.image_view1)



        btncam.setOnClickListener {
           if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
               val camera = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
               val guardadoExterno = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
               val leerExterno = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)

               if(camera == PackageManager.PERMISSION_DENIED || guardadoExterno == PackageManager.PERMISSION_DENIED || leerExterno == PackageManager.PERMISSION_DENIED){
                   //Permiso no esta habilitado
                   val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                   //mostrar popup request permiso
                   requestPermissions(permission, PERMISSION_CODE)
               }else{
                   //Permiso listo para utilizar
                   openCamera()
               }
           }else{
               //sistema es < marshmallow
               openCamera()   
           }
        }


        return root
    }

    private fun openCamera() {

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "De la camara")
        image_uri = mContext.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //es llamada cuando usuario pressiona aceptar o denegar los permiso popup

        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //Permiso aceptado del popup
                    openCamera()
                }else{
                    //permiso no fue aceptado
                    Toast.makeText(mContext, "Permiso denegado", Toast.LENGTH_SHORT)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //es llamado cuando la imagen fue capturada de la camara intent
        if(resultCode == Activity.RESULT_OK){
            //coloca la imagen capturada en image_view
            image_view.setImageURI(image_uri)
        }
    }

   /* private fun checkAndRequestPermissions(): Boolean {

        val camera = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
        val listPermissionsNeeded = ArrayList<String>()

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        return if (listPermissionsNeeded.isNotEmpty()) {
            requestPermissions(listPermissionsNeeded.toTypedArray(), 1888)


            false
        }else{
            true
        }

    }*/
}
