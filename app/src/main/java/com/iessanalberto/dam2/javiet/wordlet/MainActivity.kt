package com.iessanalberto.dam2.javiet.wordlet

import android.app.Activity
import android.graphics.Color.green
import android.graphics.ColorFilter
import android.graphics.ColorMatrix
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.EditText
import com.iessanalberto.dam2.javiet.wordlet.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        //var continuar:Boolean
        binding.resetButton.visibility=View.INVISIBLE
        binding.resultado.visibility=View.INVISIBLE
        setContentView(binding.root)
        var letra:Char
        var letraPalabra:Char
        var aumentar=true
        var contador=0;
        val palabras: List<String> = listOf("Hongos","Patata","Kotlin","Caiman")
        var acierto=0
        var palabra=palabras.shuffled().first().uppercase()
        val listaLetras=mutableListOf(
            binding.letra00,binding.letra01,binding.letra02,binding.letra03,binding.letra04,binding.letra05,
            binding.letra10,binding.letra11,binding.letra12,binding.letra13,binding.letra14,binding.letra15,
            binding.letra20,binding.letra21,binding.letra22,binding.letra23,binding.letra24,binding.letra25,
            binding.letra30,binding.letra31,binding.letra32,binding.letra33,binding.letra34,binding.letra35,
            binding.letra40,binding.letra41,binding.letra42,binding.letra43,binding.letra44,binding.letra45,
            binding.letra50,binding.letra51,binding.letra52,binding.letra53,binding.letra54,binding.letra55,
        )
        //Visibilidad Inicial del grid
        for (i in listaLetras){
            if(listaLetras.indexOf(i)>5)
                i.visibility= View.INVISIBLE
        }

        binding.button9.setOnClickListener(){
            acierto=0
            aumentar=true
            //Chivato
            binding.textView2.text=palabra
            if((contador)*6<listaLetras.size) {
                for (i in 0..5) {
                    letra=listaLetras[i+(contador)*6].text.toString().uppercase()[0]
                    letraPalabra=palabra[i]
                    if(listaLetras[i].text.isEmpty()){
                        aumentar=false
                    }
                    else if(letra == letraPalabra){
                        listaLetras[i+contador*6].setBackgroundColor(resources.getColor(R.color.green))
                        acierto++
                    }else if(palabra.contains(letra)){
                        listaLetras[i+contador*6].setBackgroundColor(resources.getColor(R.color.amarillo))
                    }
                    listaLetras[i+contador*6].isFocusable=false
                }
            }
            if (aumentar)
                contador++

            if((contador)*6<listaLetras.size&&acierto!=6) {
                for (i in (6 * contador) until 6 * (contador + 1)) {
                    listaLetras.get(i).visibility = View.VISIBLE
                }
            }else if(acierto==6){
                binding.button9.visibility=View.INVISIBLE
                binding.resetButton.visibility=View.VISIBLE
                binding.resultado.text="Feliciadades, Acertaste!"
                binding.resultado.visibility=View.VISIBLE
            }
        }
        if (contador>=6&&acierto<6){
            binding.button9.visibility=View.INVISIBLE
            binding.resetButton.visibility=View.VISIBLE
            binding.resultado.text="No, has acertado!"
            binding.resultado.visibility=View.VISIBLE
        }

        binding.resetButton.setOnClickListener(){
            aumentar=true
            contador=0
            acierto=0
            for (i in listaLetras){
                i.isFocusable=true
                if(listaLetras.indexOf(i)>5)
                    i.visibility= View.INVISIBLE
                if(i.text.isNotEmpty())
                    i.setText("")
                i.setBackgroundColor(resources.getColor(R.color.grey_light))
            }

            palabra=palabras.shuffled().first().uppercase()
            binding.button9.visibility=View.VISIBLE
            binding.resetButton.visibility=View.INVISIBLE
            binding.resultado.visibility=View.INVISIBLE
        }
    }

}