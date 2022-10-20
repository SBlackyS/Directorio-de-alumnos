package com.example.practica01v22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practica01v22.databinding.ActivityMainNuevoBinding

class MainActivityNuevo : AppCompatActivity() {
    //Vincular vistas con MainActivity
    private lateinit var binding: ActivityMainNuevoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNuevoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val parExtra = intent.extras
        //var miVar = parExtra?.getString("valor1")

        //binding.txtDato.text=miVar.toString()

        binding.btnGuardar.setOnClickListener{
            val txtNom = binding.txtNombre.text
            val txtCue = binding.txtCuenta.text
            val txtCorr = binding.txtCorreo.text
            val txtImg = binding.txtImage.text

            val intento2 = Intent(this,MainActivity::class.java)
            intento2.putExtra("mensaje","nuevo")
            intento2.putExtra("nombre","${txtNom}")
            intento2.putExtra("cuenta","${txtCue}")
            intento2.putExtra("correo","${txtCorr}")
            intento2.putExtra("image","${txtImg}")
            startActivity(intento2)
        }
    }
}