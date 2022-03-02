package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_iniciar_sesion = findViewById<Button>(R.id.btn_iniciar_sesion)
        btn_iniciar_sesion.setOnClickListener{
            val intent = Intent(this,GUI_Login::class.java)
            startActivity(intent)
        }

        val btn_crear_cuenta = findViewById<Button>(R.id.btn_crear_cuenta)
        btn_crear_cuenta.setOnClickListener{
            val intent = Intent(this,GUI_Crear_Cuenta::class.java)
            startActivity(intent)
        }

    }
}