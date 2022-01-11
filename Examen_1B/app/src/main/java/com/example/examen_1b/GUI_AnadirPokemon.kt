package com.example.examen_1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_AnadirPokemon : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombre = ""
    var tipo = ""
    var posicionEntrenador = 0
    var idEntrenadorOwner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_pokemon)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        posicionEntrenador = intent.getIntExtra("posicionEntrenador",-1)

        Log.i("posEntrendaor","${posicionEntrenador}")

        BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador : BEntrenador ->
            Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            if (indice == posicionEntrenador){
                idEntrenadorOwner = entrenador.idEntrenador
            }
        }

        var longitudListaPokemon = BBaseDeDatosMemoria.arregloPokemon.lastIndex

        BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon : BPokemon ->
            Log.i("testExamen","${pokemon.nombre} -> ${pokemon.tipo}")
            if (indice == longitudListaPokemon){
                lastId = pokemon.idPokemon
            }
        }

        nextId = lastId+1

        var txtInNombre = findViewById<TextInputEditText>(R.id.txtIn_NombrePokemon)
        var txtInTipo = findViewById<TextInputEditText>(R.id.txtIn_TipoPokemon)

        var btnAddPokemon= findViewById<Button>(R.id.btn_AddPokemon)
        btnAddPokemon.setOnClickListener {
            nombre = txtInNombre.text.toString()
            tipo = txtInTipo.text.toString()
            BBaseDeDatosMemoria.arregloPokemon.add(
                BPokemon(nextId,nombre,tipo)
            )
            BBaseDeDatosMemoria.arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(idEntrenadorOwner, nextId)
            )
            devolverRespuesta()
        }

        var btnCancelarPokemon = findViewById<Button>(R.id.btn_CancelPokemon)
        btnCancelarPokemon.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEntrenador",posicionEntrenador)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}