package com.plumbaria.e_8_1_serviciomusica

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class ServicioMusica: Service()
{
    lateinit var reproductor:MediaPlayer
    private lateinit var notificationManager: NotificationManager
    private companion object {
        val ID_NOTIFICAION:Int = 1
    }

    override fun onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show()
        reproductor = MediaPlayer.create(this, R.raw.audio)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Servicio arrancado " + startId, Toast.LENGTH_SHORT).show()
        reproductor.start()
        var intent : Intent = Intent(this, MainActivity::class.java)
        var pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0,intent, 0)
        var vibrate : LongArray = longArrayOf(0,100,200,300)
        // Cambiamos y empezamos a trabajar para API >= 16, usaremos el build()
        var notification:Notification = Notification.Builder(this)
            .setContentTitle("Reproduciendo música")
            .setContentText("Información adicional")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setVibrate(vibrate)
            .setLights(-0xff01,0,0)
            .build()
        notification.flags = notification.flags or Notification.FLAG_SHOW_LIGHTS

        notificationManager.notify(ID_NOTIFICAION, notification)
        return  START_STICKY // El sistema tratará de volver a iniciar el servicio automáticamente
    }

    override fun onStart(intent: Intent?, startId: Int) {
        Toast.makeText(this, "Servicio arrancado " + startId, Toast.LENGTH_SHORT).show()
        reproductor.start()
    }

    override fun onDestroy() {
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show()
        notificationManager.cancel(ID_NOTIFICAION)
        reproductor.stop()
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}