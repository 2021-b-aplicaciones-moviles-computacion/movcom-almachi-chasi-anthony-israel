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

class GUI_Home : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_home)
        Log.i("ciclo-vida", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewEntrenador = findViewById<ListView>(R.id.lv_Entrenador)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloEntrenador
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewEntrenador)

        val btnAnadirEntrenador = findViewById<Button>(R.id.btn_AnadirEntrenador)
        btnAnadirEntrenador.setOnClickListener {
            val intentAddEntrenador = Intent(this, GUI_AnadirEntrenador::class.java)
            startActivity(intentAddEntrenador)
        }

    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida", "onDestroy")
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(GUI_EditarEntrenador::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarEntrenador(idItemSeleccionado)
                return true
            }
            R.id.mi_pokemons -> {
                Log.i("context-menu", "Pokemons: ${idItemSeleccionado}")
                abrirActividadConParametros(GUI_Pokemon::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarEntrenador = Intent(this, clase)
        intentEditarEntrenador.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarEntrenador)
    }

    fun eliminarEntrenador(
        posicioEntrenadorEnliminar: Int
    ) {
        val listViewEntrenador = findViewById<ListView>(R.id.lv_Entrenador)

        var entrenadorAeliminar = BBaseDeDatosMemoria.arregloEntrenador.elementAt(posicioEntrenadorEnliminar)
        var idEntrenadorAeliminar = entrenadorAeliminar.idEntrenador

        var auxListaEntrenadorXpokemon = arrayListOf<BEntrenadorXPokemon>()

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon: BEntrenadorXPokemon ->
            if(idEntrenadorAeliminar != entrenadorXpokemon.idEntrenador){
                auxListaEntrenadorXpokemon.add(entrenadorXpokemon)
            }
        }

        BBaseDeDatosMemoria.arregloEntrenador.removeAt(posicioEntrenadorEnliminar)
        BBaseDeDatosMemoria.arregloEntrenadorXPokemon = auxListaEntrenadorXpokemon

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloEntrenador
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}