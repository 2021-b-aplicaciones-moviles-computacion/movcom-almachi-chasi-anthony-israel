package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_AddPokemon : AppCompatActivity() {

    var trainer_owner = FirestoreTrainer("","","")
    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")
    val pokemons = db.collection("AvailablePokemons")
    var idTipoPokemonSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_add_pokemon)
    }

    override fun onStart() {
        super.onStart()

        trainer_owner = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!
        val trainerSubcollection = trainers.document("${trainer_owner.trainerId}").collection("Pokemons")

        val in_pokemon_name = findViewById<EditText>(R.id.in_pokemon_name)
        val sp_pokemon_specie = findViewById<Spinner>(R.id.sp_pokemon_specie)
        val tv_pokemon_type = findViewById<TextView>(R.id.tv_pokemon_type)

        val btn_confirm_add_pokemon = findViewById<Button>(R.id.btn_confirm_add_pokemon)
        val btn_cancel_add_pokemon = findViewById<Button>(R.id.btn_cancel_add_pokemon)
        btn_cancel_add_pokemon.setOnClickListener {
            devolverRespuesta()
        }

        pokemons.get().addOnSuccessListener { result ->

            val pokemonSpecie = arrayListOf<String>()
            val pokemonType = arrayListOf<String>()

            for (document in result) {
                pokemonSpecie.add(document.data.get("pokemon_specie").toString())
                pokemonType.add(document.data.get("pokemon_type").toString())
            }

            val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemonSpecie)
            sp_pokemon_specie.adapter = adaptador

            sp_pokemon_specie.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    positionTipoPokemon: Int,
                    p3: Long
                ) {
                    idTipoPokemonSeleccionado = positionTipoPokemon
                    Log.i("pokemon seleccionado", "${idTipoPokemonSeleccionado}")
                    tv_pokemon_type.setText("${pokemonType.elementAt(idTipoPokemonSeleccionado)}")
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            btn_confirm_add_pokemon.setOnClickListener {
                //Log.i("PokemonToAdd","${in_pokemon_name.text}, ${pokemonSpecie.elementAt(idTipoPokemonSeleccionado)}, ${pokemonType.elementAt(idTipoPokemonSeleccionado)}")
                var pokemon = hashMapOf(
                    "pokemon_owner" to trainer_owner.trainerId.toString(),
                    "pokemon_name" to in_pokemon_name.text.toString(),
                    "pokemon_specie" to pokemonSpecie.elementAt(idTipoPokemonSeleccionado),
                    "pokemon_type" to pokemonType.elementAt(idTipoPokemonSeleccionado)
                )
                trainerSubcollection.add(pokemon).addOnSuccessListener {
                    in_pokemon_name.text.clear()
                    Toast.makeText(this,"Pokemon registrado exitosamente",Toast.LENGTH_SHORT).show();
                    Log.i("Add-Pokemon","Success")
                }.addOnFailureListener{
                    Log.i("Add-Pokemon","Failed")
                }
            }

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