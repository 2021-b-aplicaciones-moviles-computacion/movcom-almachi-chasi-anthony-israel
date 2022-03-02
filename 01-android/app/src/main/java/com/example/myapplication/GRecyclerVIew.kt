package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class GRecyclerVIew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()

        listaEntrenador.add(
            BEntrenador("Vicente","1234567898")
        )
        listaEntrenador.add(
            BEntrenador("Adrian","1234567898")
        )

        val recyclerViewEntrenador = findViewById<RecyclerView>(R.id.rv_entrenadores)

        inicializarRecyclerView(listaEntrenador,this,recyclerViewEntrenador)

    }

    fun inicializarRecyclerView(
        lista: List<BEntrenador>,
        actividad: GRecyclerVIew,
        recyclerView: RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptorNombreCedula(
            actividad,
            lista,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }

}