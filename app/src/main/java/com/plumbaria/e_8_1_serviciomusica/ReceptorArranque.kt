package com.plumbaria.e_8_1_serviciomusica

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReceptorArranque : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var i = Intent(context, ServicioSocorro::class.java)
        context.startService(i)
    }
}
