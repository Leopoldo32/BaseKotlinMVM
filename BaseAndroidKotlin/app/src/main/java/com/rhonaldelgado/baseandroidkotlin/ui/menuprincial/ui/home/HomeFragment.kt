package com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rhonaldelgado.baseandroidkotlin.R
import com.rhonaldelgado.baseandroidkotlin.ui.menuprincial.menuprincipal

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mContext: Context
    private lateinit var padreActivity: menuprincipal
    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null


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



        btncam.setOnClickListener {
            if(checkCameraHardware(mContext)){
                Toast.makeText(mContext, "tiene camara ", Toast.LENGTH_SHORT).show()

                checkAndRequestPermissions()
                   

            }else{
                Toast.makeText(mContext, "NO tiene camara ", Toast.LENGTH_SHORT).show()
            }
        }


        return root
    }

    /** A safe way to get an instance of the Camera object. */
    private fun getCameraInstance(): Camera? {
        return try {
            Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            return null // returns null if camera is unavailable
        }
    }

    /** Check if this device has a camera */
    private fun checkCameraHardware(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }


    private fun checkAndRequestPermissions(): Boolean {

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

    }
}
