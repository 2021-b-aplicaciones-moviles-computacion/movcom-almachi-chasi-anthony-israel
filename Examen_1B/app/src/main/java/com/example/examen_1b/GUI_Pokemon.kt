package com.example.examen_1b

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

class GUI_Pokemon : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idEntrenadorOwner = 0
    var posicionEntrenador = 0

    var pokemonLista = arrayListOf<String>()

    var resultAddNewPokemon = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionEntrenador = data?.getIntExtra("posicionEntrenador",0)!!
            }
        }

    }

    var resultEditarPokemon = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionEntrenador = data?.getIntExtra("posicionEntrenadoreditar",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_pokemon)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        pokemonLista = arrayListOf()

        posicionEntrenador = intent.getIntExtra("posicionEditar",1)

        Log.i("posEntrendaor","${posicionEntrenador}")

        var idPokemon = arrayListOf<Int>()

        val tvNombreBEntrenadorXPokemon = findViewById<TextView>(R.id.tv_NombreEntrenadorXPokemon)

        BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador : BEntrenador ->
            Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            if (indice == posicionEntrenador){
                idEntrenadorOwner = entrenador.idEntrenador
                var label = "Entrenador: ${entrenador.nombre}"
                tvNombreBEntrenadorXPokemon.setText(label)
            }
        }

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon : BEntrenadorXPokemon ->
            if (idEntrenadorOwner == entrenadorXpokemon.idEntrenador){
                idPokemon.add(entrenadorXpokemon.idPokemon)
            }
        }

        idPokemon.forEach{ idPokemon:Int ->
            BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon : BPokemon ->
                if (idPokemon == pokemon.idPokemon){
                    pokemonLista.add(pokemon.nombre.toString())
                }
            }
        }

        val listViewPokemon = findViewById<ListView>(R.id.lv_Pokemon)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            pokemonLista
        )
        listViewPokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnAddPokemon = findViewById<Button>(R.id.btn_NuevoPokemon)
        btnAddPokemon.setOnClickListener {
            abrirActividadAddPokemon(GUI_AnadirPokemon::class.java)
        }

        val btnAtrasPokemon = findViewById<Button>(R.id.btn_AtrasPokemon)
        btnAtrasPokemon.setOnClickListener {
            val intentAtrasPokemon = Intent(this, GUI_Home::class.java)
            startActivity(intentAtrasPokemon)
        }

        val btnListBD = findViewById<Button>(R.id.btn_ListBD)
        btnListBD.setOnClickListener {
            BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEach{entrenadorXpokemon: BEntrenadorXPokemon ->
                Log.i("PokemonsRestantes","${entrenadorXpokemon.idEntrenador} -> ${entrenadorXpokemon.idPokemon}")
            }
        }

        this.registerForContextMenu(listViewPokemon)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pokemon, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        var nombrePokemon: String = pokemonLista.elementAt(id)
        BBaseDeDatosMemoria.arregloPokemon.forEach{ pokemon: BPokemon ->
            if(nombrePokemon == pokemon.nombre){
                idItemSeleccionado = pokemon.idPokemon
            }
        }
        Log.i("idPokemon", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarPokemon -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarPokemon(GUI_EditarPokemon::class.java)
                return true
            }
            R.id.mi_eliminarPokemon -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarPokemon(idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarPokemon(
        clase: Class<*>
    ) {
        val intentEditarPokemon = Intent(this, clase)
        intentEditarPokemon.putExtra("idPokemon", idItemSeleccionado)
        intentEditarPokemon.putExtra("posicionEntrenadoreditar",posicionEntrenador)
        resultEditarPokemon.launch(intentEditarPokemon)
    }

    fun abrirActividadAddPokemon(
        clase: Class<*>
    ) {
        val intentAddNewPokemon = Intent(this, clase)
        intentAddNewPokemon.putExtra("posicionEntrenador",posicionEntrenador)
        Log.i("positionSend","${posicionEntrenador}")
        resultAddNewPokemon.launch(intentAddNewPokemon)
    }

    fun eliminarPokemon(
        idPokemonAeliminar: Int
    ){
        val listViewPokemon = findViewById<ListView>(R.id.lv_Pokemon)
        var nombrePokemonAeliminar = ""

        BBaseDeDatosMemoria.arregloPokemon.forEach{ pokemon: BPokemon ->
            if(idPokemonAeliminar == pokemon.idPokemon){
                nombrePokemonAeliminar = pokemon.nombre.toString()
            }
        }

        var listaPokemonsSobrantes = arrayListOf<BEntrenadorXPokemon>()
        Log.i("IdPokemonAeliminar","${idPokemonAeliminar}")

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon: BEntrenadorXPokemon->
            if(!((idPokemonAeliminar == entrenadorXpokemon.idPokemon) and (idEntrenadorOwner == entrenadorXpokemon.idEntrenador))){
                listaPokemonsSobrantes.add(entrenadorXpokemon)
                Log.i("EliminarPokemon","${entrenadorXpokemon.idEntrenador} -> ${entrenadorXpokemon.idPokemon}")
            }
        }

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon = listaPokemonsSobrantes

        pokemonLista.remove(nombrePokemonAeliminar)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            pokemonLista
        )
        listViewPokemon.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}