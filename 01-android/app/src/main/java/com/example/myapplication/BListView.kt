package com.example.myapplication

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
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var idItemSelected = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo: ArrayList<Int> = arrayListOf(1,2,3,4,5)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDatosMemoria.arregloEntrenador
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAddItem = findViewById<Button>(R.id.btn_add_list_view)
        botonAddItem.setOnClickListener {
            anadirItem(adaptador, BBaseDatosMemoria.arregloEntrenador, 1)
        }

        /*listView.setOnItemLongClickListener {parent, view, position, id ->
            Log.i("List-View","LONG CLICK ${arreglo[position]}")
            return@setOnItemLongClickListener true
        }*/

        // 1) Registrar menu contextual
        this.registerForContextMenu(listView)

    }

    // 2) Seleccionar el XML a usar en el menu contextual

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
        idItemSelected = id
        Log.i("context-menu","ID ${id}")
    }

    // 3) Que opccion se selecciono

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_edit -> {
                Log.i("context-menu", "Edit position: ${idItemSelected}")
                abrirDialogo()
                return true
            }
            R.id.mi_delete -> {
                Log.i("context-menu", "Delete position: ${idItemSelected}")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder  = AlertDialog.Builder(this)
        builder.setTitle("Title")
        //builder.setMessage("Description")

        builder.setPositiveButton("Aceptar") { dialog, which ->
            Log.i("Dialog", "Hola")
        }

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val options = resources.getStringArray(
            R.array.string_array_options_dialog
        )

        val previousSelection = booleanArrayOf(
            true,false,false
        )

        builder.setMultiChoiceItems(options, previousSelection){
            dialog,which, isChecked -> Log.i("dialogo","Dio click en el item ${which}")
        }

        val dialogo = builder.create()
        dialogo.show()

    }


    fun anadirItem(
        adaptador: ArrayAdapter<BEntrenador>,
        arreglo: ArrayList<BEntrenador>,
        valor: Int
    ){
        arreglo.add(BEntrenador("otro","x@x.com"))
        adaptador.notifyDataSetChanged()
    }

}