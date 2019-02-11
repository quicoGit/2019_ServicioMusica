package com.plumbaria.e_8_1_serviciomusica

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val PERMISOS = arrayOf(Manifest.permission.VIBRATE, Manifest.permission.RECEIVE_BOOT_COMPLETED)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arrancar:Button = findViewById<Button>(R.id.boton_arrancar)
        arrancar.setOnClickListener(this)

        var detener:Button = findViewById(R.id.boton_detener)
        detener.setOnClickListener(this)

        if (!hayPermiso(Manifest.permission.VIBRATE) || !hayPermiso(Manifest.permission.RECEIVE_BOOT_COMPLETED)) {
            ActivityCompat.requestPermissions(this,
                PERMISOS,
                123)
        }

    }

    /*
    Tratamos el onclick
     */
    override fun onClick(v: View?) {
        var intent:Intent = Intent(this, ServicioSocorro::class.java)
        when (v?.id) {
            R.id.boton_arrancar -> {
                startService(intent)
            }
            R.id.boton_detener -> {
                stopService(intent)
            }
        }
    }

    /* permisos */
    private fun hayPermiso(permiso: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permiso) == PackageManager.PERMISSION_GRANTED
    }

}
