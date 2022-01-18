package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

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

        EBaseDeDatos.TablaUsuario = EsqliteHelperUsuario(this)

        if (EBaseDeDatos.TablaUsuario != null) {
            val idQuemado = 2
            EBaseDeDatos.TablaUsuario?.crearUsuarioFormulario(
                "Adrian",
                "Adrian desc"
            )
            var consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre} id ${consulta?.id}")
            EBaseDeDatos.TablaUsuario?.actualizarUsuarioFormulario(
                "Vicente",
                "Vicenet desc",
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
            EBaseDeDatos.TablaUsuario?.eliminarUsuarioFormulario(
                idQuemado
            )
            consulta = EBaseDeDatos.TablaUsuario?.consultarUsuarioPorId(
                idQuemado
            )
            Log.i("bdd", "Primera Consulta: ${consulta?.nombre}")
        }


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

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito.setOnClickListener {
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intentConRespuesta,CODIGO_RESPUESTA_INTENT_IMPLICITO)
        }

        val botonRecyclerView = findViewById<Button>(R.id.btn_ir_recycler_view)
        botonRecyclerView.setOnClickListener {
            abrirActividadConParametros(GRecyclerVIew::class.java)
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
        //resultLauncher.launch(intentExplicit)

        startActivityForResult(intentExplicit, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if (requestCode == RESULT_OK) {
                    Log.i("intent", "${data?.getStringExtra("nombreModificado")}")
                }
                if (resultCode == RESULT_CANCELED){
                    Log.i("intent", "Cancelado")
                }
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO -> {
                if (requestCode == RESULT_OK) {
                    if (data != null){
                        val uri: Uri = data.data!!
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indiceTelefono = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val telefono = cursor?.getString(
                            indiceTelefono!!
                        )
                        cursor?.close()
                        Log.i("intent-epn","Telefono ${telefono}")
                    }
                }
            }
        }
    }

    fun irActividad(
        clase: Class<*>,
    ){
        val intent = Intent(this,clase)
    }

}
