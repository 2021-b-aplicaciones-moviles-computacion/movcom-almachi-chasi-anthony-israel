package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.w3c.dom.Document

class GUI_TrainersList : AppCompatActivity() {

    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<FirestoreTrainer>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_trainers_list)
    }

    override fun onStart() {
        super.onStart()

        updateTrainerList()

        val btn_add_trainer = findViewById<Button>(R.id.btn_add_trainer)
        btn_add_trainer.setOnClickListener {
            val intent = Intent(this, GUI_AddTrainer::class.java)
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
        inflater.inflate(R.menu.trainer_menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var trainerSelected:FirestoreTrainer = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${trainerSelected.trainerId}")
                val openEditTrainer = Intent(this, GUI_EditTrainer::class.java)
                openEditTrainer.putExtra("PokemonTrainer",trainerSelected)
                startActivity(openEditTrainer)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                trainers.document("${trainerSelected.trainerId}").delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-TRAINER","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-TRAINER","Failure")
                    }
                updateTrainerList()
                return true
            }
            R.id.mi_pokemons -> {
                Log.i("context-menu", "Pokemons: ${idItemSeleccionado}")
                val openPokemonList = Intent(this, GUI_PokemonList::class.java)
                openPokemonList.putExtra("PokemonTrainer",trainerSelected)
                startActivity(openPokemonList)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updateTrainerList(){
        val lb_trainers = findViewById<ListView>(R.id.lb_trainers)

        trainers.get().addOnSuccessListener{ result ->

            var trainersList = arrayListOf<FirestoreTrainer>()

            for (document in result) {
                trainersList.add(
                    FirestoreTrainer(
                        document.id.toString(),
                        document.get("trainer_name").toString(),
                        document.get("trainer_age").toString()
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                trainersList
            )

            lb_trainers.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lb_trainers)

        }.addOnFailureListener{
            Log.i("ERROR", "Faiuler retreving trainers")
        }
    }

}