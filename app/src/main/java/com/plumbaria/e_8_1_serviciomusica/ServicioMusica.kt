package com.plumbaria.e_8_1_serviciomusica

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class ServicioMusica: Service()
{
    lateinit var reproductor:MediaPlayer

    override fun onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show()
        reproductor = MediaPlayer.create(this, R.raw.audio)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Servicio arrancado " + startId, Toast.LENGTH_SHORT).show()
        reproductor.start()
        return  START_STICKY // El sistema tratará de volver a iniciar el servicio automáticamente
    }

    override fun onDestroy() {
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show()
        reproductor.stop()
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}