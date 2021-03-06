package com.example.examen_1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_EditarEntrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_gui_editar_entrenador)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionEntrenador = intent.getIntExtra("posicionEditar",1)

        val txtInNombreEditar = findViewById<TextInputEditText>(R.id.txtIn_NombreEntrenadorEditar)
        val txtInEdadEditar = findViewById<TextInputEditText>(R.id.txtIn_EdadEditar)

        BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador : BEntrenador ->
            Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            if (indice == posicionEntrenador){
                txtInNombreEditar.setText(entrenador.nombre)
                txtInEdadEditar.setText(entrenador.edad)
            }
        }

        val btnActualizarEditar = findViewById<Button>(R.id.btn_ActualizarEditar)
        btnActualizarEditar.setOnClickListener {
            BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador: BEntrenador ->
                if (indice == posicionEntrenador){
                    Log.i("editar","${txtInNombreEditar.text.toString()}")
                    entrenador.nombre = (txtInNombreEditar.text.toString())
                    entrenador.edad = (txtInEdadEditar.text.toString())
                }
            }
            val intentEditSucces = Intent(this, GUI_Home::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_CancelarEditar)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, GUI_Home::class.java)
            startActivity(intentEditCancel)
        }

    }
}