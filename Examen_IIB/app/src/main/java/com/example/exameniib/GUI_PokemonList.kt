package com.example.exameniib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_PokemonList : AppCompatActivity() {

    var idItemSeleccionado = 0
    var trainer_owner = FirestoreTrainer("","","")
    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")
    var adaptador: ArrayAdapter<FirestorePokemon>? = null

    var resultAddNewPokemon = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                trainer_owner = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!
            }
        }
    }

    var resultEditPokemon = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                trainer_owner = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_pokemon_list)
    }

    override fun onStart() {
        super.onStart()

        trainer_owner = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!

        updatePokemonList()

        val lbl_trainer_owner = findViewById<TextView>(R.id.lbl_trainer_owner)
        lbl_trainer_owner.setText("Entrenador: ${trainer_owner.trainerName}")

        val btn_add_pokemon = findViewById<Button>(R.id.btn_add_pokemon)
        btn_add_pokemon.setOnClickListener {
            val openAddPokemonGUI = Intent(this, GUI_AddPokemon::class.java)
            openAddPokemonGUI.putExtra("PokemonTrainer",trainer_owner)
            resultAddNewPokemon.launch(openAddPokemonGUI)
        }

        val btn_back_to_trainers = findViewById<Button>(R.id.btn_back_trainer_from_pokemon)
        btn_back_to_trainers.setOnClickListener {
            val intent = Intent(this, GUI_TrainersList::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.pokemon_menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("id entrenadorXpokemon", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var pokemonSelected:FirestorePokemon = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editarPokemon -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                val openEditPokemon = Intent(this, GUI_EditPokemon::class.java)
                openEditPokemon.putExtra("PokemonTrainer",trainer_owner)
                openEditPokemon.putExtra("Pokemon",pokemonSelected)
                resultEditPokemon.launch(openEditPokemon)
                return true
            }
            R.id.mi_eliminarPokemon -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val trainerSubcollection = trainers.document("${trainer_owner.trainerId}")
                    .collection("Pokemons")
                    .document("${pokemonSelected.pokemonId}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-POKEMON","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-POKEMON","Failure")
                    }
                updatePokemonList()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updatePokemonList(){

        val trainerSubcollection = trainers.document("${trainer_owner.trainerId}")
            .collection("Pokemons")
            .whereEqualTo("pokemon_owner","${trainer_owner.trainerId}")


        val lv_pokemon_list = findViewById<ListView>(R.id.lv_pokemon)

        trainerSubcollection.get().addOnSuccessListener { result ->

            var pokemonsList = arrayListOf<FirestorePokemon>()

            for (document in result){
                pokemonsList.add(
                    FirestorePokemon(
                        document.id.toString(),
                        document.data.get("pokemon_name").toString(),
                        document.data.get("pokemon_owner").toString(),
                        document.data.get("pokemon_specie").toString(),
                        document.data.get("pokemon_type").toString(),
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                pokemonsList
            )

            lv_pokemon_list.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_pokemon_list)

        }

    }

}