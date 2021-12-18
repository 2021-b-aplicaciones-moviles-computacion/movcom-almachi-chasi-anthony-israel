package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                Log.i("intent","${data?.getStringExtra("nombreModificado")}")
                Log.i("intent","${data?.getIntExtra("edadModificado",0)}")
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonIrCicliVida = findViewById<Button>(R.id.btn_ir_ciclo_vida)
        botonIrCicliVida.setOnClickListener {
            val intent = Intent(this, ACicloVida::class.java)
            startActivity(intent)
        }

        val botonIrListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonIrListView.setOnClickListener {
            //irActividad(BListView::class.java)
            val intent = Intent(this, BListView::class.java)
            startActivity(intent)
        }

        val botonIntentExplicit = findViewById<Button>(R.id.btn_intent)
        botonIntentExplicit.setOnClickListener {
            abrirActividadConParametros(CIntentExplicitoParametros::class.java)
        }

    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicit = Intent(this,clase)
        intentExplicit.putExtra("nombre","Adrian")
        intentExplicit.putExtra("apellido","Eguez")
        intentExplicit.putExtra("edad","352")
        intentExplicit.putExtra("a",BEntrenador("a","b"))
        resultLauncher.launch(intentExplicit)

        //startActivityForResult(intent, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun irActividad(
        clase: Class<*>,
    ){
        val intent = Intent(this,clase)

    }

}
