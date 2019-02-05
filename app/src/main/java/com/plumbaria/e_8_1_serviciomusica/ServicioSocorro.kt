package com.plumbaria.e_8_1_serviciomusica

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class ServicioSocorro: Service() {

    lateinit var mediaPlayer: MediaPlayer

    private lateinit var notificationManager: NotificationManager
    private companion object {
        val ID_NOTIFICAION:Int = 1
    }
    private lateinit var notificacion:Notification

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.socorro)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var intent : Intent = Intent(this, MainActivity::class.java)
        var pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0,intent, 0)
        var vibrate : LongArray = longArrayOf(500, 500, 500, 500, 500, 500, 500, 1000, 500, 1000, 500, 1000,
            500, 500, 500, 500, 500, 500)

        notificacion =  Notification.Builder(this)
            .setContentTitle("MENSAJE SOCORRO")
            .setContentText("Información adicional")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setVibrate(vibrate)
            .setLights(-0xff01,0,0)
            .build()

        notificacion.flags = notificacion.flags or Notification.FLAG_SHOW_LIGHTS

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var handler:Handler = Handler()
        handler.postDelayed(object : Runnable{
            var servicioSocorro = this@ServicioSocorro
            override fun run() {
                notificationManager.notify(
                    ID_NOTIFICAION,
                    servicioSocorro.notificacion
                )
                mediaPlayer.start()
                Toast.makeText(this@ServicioSocorro, "Ha llegado al handler", Toast.LENGTH_LONG).show()
            }
        },5000)


        return  Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}