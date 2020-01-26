/*
* Universidad del Valle de Guatemala
* Programacion de Aplicaciones moviles y Juegos
* Julio Roberto Herrera Saban 19402
* 01/2020
*
* MainActivity de FitApp Laboratorio 2
* Cuenta las vueltas de un ejercicio, al llegar a 10 muestra un toast y una imagen
* Al dejar presionado el boton de añadir vueltas muestra cuantas vueltas faltan
* Al llegar a 20 ya no se dan mas vueltas y se da la opcion de reiniciar con su respectivo boton
* */
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
                trophyImage.visibility = View.VISIBLE //Hacer visible la imagen inicial
            } else if (laps == 20) {
                trophyImage.setImageResource(R.drawable.award) //Cambiar la imagen del ImageView
                showToast()
            } else if (laps > 20) {
                laps = 20
            }
            textLaps.setText(laps.toString()) //Actualizar el texto cada ciclo
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
            trophyImage.visibility = View.INVISIBLE //Esconder la imagen del trofeo
        }
    }

    fun showToast() {
        val context: Context = applicationContext
        val inflater: LayoutInflater = layoutInflater
        val toastRoot: View = inflater.inflate(R.layout.custom_toast, null)
        /*
        * Es necesario obtener la vista (toastRoot) para que al llamar los elementos (toast_text) por id, estos no regresen como null.
        * */
        val toastText: TextView = toastRoot.findViewById(R.id.toast_text) //Esto daría null si no se obtuviera de la vista del custom_toast
        toastText.text = resources.getString(R.string.toast_text) + (20 - laps) + " " + resources.getString(R.string.laps) //Estos textos se obtienen del strings.xml
        if (laps == 20) {
            toastText.text = resources.getString(R.string.congrats)
        }
        val toast: Toast = Toast(context) // Especificando las opciones del toast
        toast.view = toastRoot
        toast.setGravity(Gravity.BOTTOM,0,50)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    fun vibrationDadadadaaadaaadadada() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibratePattern3 = longArrayOf(200,200,50,200,50,200,175,450,50,450,200,150,50,150,50,150) //Vibration Pattern, crea la "cancion" con la vibracion
        v.vibrate(VibrationEffect.createWaveform(vibratePattern3, -1)) //Solo funciona con minSDKversion 26 en app gradle
    }
}
