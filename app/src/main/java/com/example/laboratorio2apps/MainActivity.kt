package com.example.laboratorio2apps

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.os.Vibrator
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.VibrationEffect
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    //Variable que cuenta las vueltas y se le suman
    var laps = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Elementos de vista
        val btnAddCount: Button = findViewById(R.id.buttonAddCount) //boton para anadir cuentas
        val textLaps: TextView = findViewById(R.id.laps) //Texto que muestra las vueltas
        val trophyImage: ImageView = findViewById(R.id.trophy) //Imagen de trofeo
        val btnRestart: Button = findViewById(R.id.buttonRestore) //boton que reinicia las vueltas
        //Click del boton para anadir vueltas
        btnAddCount.setOnClickListener {
            laps++
            if (laps == 10) {
                showToast()
                vibrationDadadadaaadaaadadada()
                trophyImage.visibility = View.VISIBLE
            } else if (laps == 20) {
                trophyImage.setImageResource(R.drawable.award)
                showToast()
            } else if (laps > 20) {
                laps = 20
            }
            textLaps.setText(laps.toString())
        }
        //Click largo en el boton para anadir vueltas
        btnAddCount.setOnLongClickListener {
            showToast()
            true
        }
        //Click del boton para reiniciar vueltas
        btnRestart.setOnClickListener {
            laps = 0
            textLaps.setText(laps.toString())
            trophyImage.visibility = View.INVISIBLE
        }
    }

    fun showToast() {
        val context: Context = applicationContext
        val inflater: LayoutInflater = layoutInflater
        val toastRoot: View = inflater.inflate(R.layout.custom_toast, null)
        val toastText: TextView = toastRoot.findViewById(R.id.toast_text)
        toastText.text = resources.getString(R.string.toast_text) + (20 - laps) + " " + resources.getString(R.string.laps)
        if (laps == 20) {
            toastText.text = resources.getString(R.string.congrats)
        }
        val toast: Toast = Toast(context)
        toast.view = toastRoot
        toast.setGravity(Gravity.BOTTOM,0,50)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    fun vibrationDadadadaaadaaadadada() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibratePattern3 = longArrayOf(200,200,50,200,50,200,175,450,50,450,200,150,50,150,50,150)
        v.vibrate(VibrationEffect.createWaveform(vibratePattern3, -1)) //Solo funciona con minSDKversion 26 en app gradle
    }
}
