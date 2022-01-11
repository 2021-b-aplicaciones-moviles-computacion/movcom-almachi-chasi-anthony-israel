package com.example.examen_1b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_EditarPokemon : AppCompatActivity() {

    var posicionEntrenador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_editar_pokemon)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idPokemon = intent.getIntExtra("idPokemon",1)
        posicionEntrenador = intent.getIntExtra("posicionEntrenadoreditar",1)

        val txtInNombreEditarPokemon = findViewById<TextInputEditText>(R.id.txtIn_NombrePokemonEdit)
        val txtInTipoEditarPokemon = findViewById<TextInputEditText>(R.id.txtIn_TipoPokemonEdit)

        BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon : BPokemon ->
            if (idPokemon == pokemon.idPokemon){
                txtInNombreEditarPokemon.setText(pokemon.nombre)
                txtInTipoEditarPokemon.setText(pokemon.tipo)
            }
        }

        val btnActualizarEditarPokemon = findViewById<Button>(R.id.btn_ActualizarEditarPokemon)
        btnActualizarEditarPokemon.setOnClickListener {
            BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon: BPokemon ->
                if (idPokemon == pokemon.idPokemon){
                    Log.i("editar","${txtInNombreEditarPokemon.text.toString()}")
                    pokemon.nombre = (txtInNombreEditarPokemon.text.toString())
                    pokemon.tipo = (txtInTipoEditarPokemon.text.toString())
                }
            }
            devolverRespuesta()
        }

        val btnCancelarEditarPokemon = findViewById<Button>(R.id.btn_CancelarEditarPokemon)
        btnCancelarEditarPokemon.setOnClickListener{
            devolverRespuesta()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEntrenadoreditar",posicionEntrenador)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}