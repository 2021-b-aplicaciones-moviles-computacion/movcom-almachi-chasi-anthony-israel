package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_EditPokemon : AppCompatActivity() {

    var trainer_owner = FirestoreTrainer("","","")
    var current_pokemon = FirestorePokemon("","","","","")
    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_edit_pokemon)
    }

    override fun onStart() {
        super.onStart()

        trainer_owner = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!
        current_pokemon = intent.getParcelableExtra<FirestorePokemon>("Pokemon")!!

        val in_pokemon_name = findViewById<EditText>(R.id.in_edit_name_pokemon)
        in_pokemon_name.setText("${current_pokemon.pokemonName}")
        val tv_pokemon_specie = findViewById<TextView>(R.id.tv_edit_pokemon_specie)
        tv_pokemon_specie.setText("${current_pokemon.pokemonSpecie}")
        val tv_pokemon_type = findViewById<TextView>(R.id.tv_edit_pokemon_type)
        tv_pokemon_type.setText("${current_pokemon.pokemonType}")

        val btn_back_to_pokemon_list = findViewById<Button>(R.id.btn_cancel_update_pokemon)
        btn_back_to_pokemon_list.setOnClickListener {
            devolverRespuesta()
        }

        val btn_confirm_update_pokemon = findViewById<Button>(R.id.btn_confirm_update_pokemon)
        btn_confirm_update_pokemon.setOnClickListener {
            trainers.document("${trainer_owner.trainerId}")
                    .collection("Pokemons")
                    .document("${current_pokemon.pokemonId}")
                    .update(
                        "pokemon_name" ,in_pokemon_name.text.toString()
                    )
            Toast.makeText(this,"Pokemon actualizado exitosamente",Toast.LENGTH_SHORT).show()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("PokemonTrainer",trainer_owner)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}