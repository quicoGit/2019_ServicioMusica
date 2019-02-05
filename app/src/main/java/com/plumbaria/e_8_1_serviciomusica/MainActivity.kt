package com.plumbaria.e_8_1_serviciomusica

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arrancar:Button = findViewById<Button>(R.id.boton_arrancar)
        arrancar.setOnClickListener(this)

        var detener:Button = findViewById(R.id.boton_detener)
        detener.setOnClickListener(this)
    }

    /*
    Tratamos el onclick
     */
    override fun onClick(v: View?) {
        //var intent:Intent = Intent(this, ServicioMusica::class.java)
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

}
