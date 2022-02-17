package com.example.proyectofirebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import android.view.View
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton_login = findViewById<Button>(R.id.btn_login)
        boton_login.setOnClickListener {
            llamarLoginUsuario()
        }

        val boton_logout = findViewById<Button>(R.id.btn_logout)
        boton_logout.setOnClickListener {
            solicitarSalirDelAplicativo()
        }

        val boton_producto = findViewById<Button>(R.id.btn_ir_producto)
        boton_producto.setOnClickListener {
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)
        }

        val boton_restaurante = findViewById<Button>(R.id.btn_it_restaurante)
        boton_restaurante.setOnClickListener {
            val intent = Intent(
                this,
                DRestaurante::class.java
            )
            startActivity(intent)
        }

    }

    fun llamarLoginUsuario(){
        val providers = arrayListOf(
            // lista de los proveedores
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html"
                )
                .build(),
            200
        )
    }

    fun onAtivityResuly(requestCode:Int, resultCode:Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode){
            200-> {
                if(resultCode == Activity.RESULT_OK){
                    val usuario: IdpResponse? = IdpResponse.fromResultIntent(data)
                    if (usuario != null){
                        if (usuario.isNewUser == true){
                            Log.i("firebase-login","Nuevo Usuario")
                            registerUsuarioPorPrimeraVez(usuario)
                        }else {
                            Log.i("firebase-login","Usuario Antiguo")
                        }
                    }
                }else{
                    Log.i("firebase-login","El usuario cancelo")
                }
            }
        }
    }

    fun registerUsuarioPorPrimeraVez(usuario: IdpResponse){
        val usuarioLogeado = FirebaseAuth
            .getInstance()
            .getCurrentUser()
        if(usuario.email != null && usuarioLogeado != null){
            val db = Firebase.firestore
            val roles = arrayListOf("usuario")
            val email = usuario.email
            val uid = usuarioLogeado.uid

            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to roles,
                "uid" to uid,
                "email" to email.toString()
            )

            db.collection("usuario")
                .document(email.toString())
                .set(nuevoUsuario)
                .addOnSuccessListener {
                    Log.i("firebas-firestore","Se creo")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener{
                    Log.i("firebase-firestore","Fallo")
                }

        }else{
            Log.i("firebase-login","Error no mail ni usuario")
        }
    }

    fun setearUsuarioFirebase(){
        val instanceAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanceAuth.currentUser
        if(usuarioLocal != null){
            if(usuarioLocal.email != null){
                val db = Firebase.firestore
                val referencia =db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())

                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioCargado:FirestoreUsuarioDto? =
                            it.toObject(FirestoreUsuarioDto::class.java)
                        if(usuarioCargado != null){
                            BAuthUsuario.usuario = usuarioCargado
                        }
                        setearBienvenida()
                        Log.i("firebase-firestore","Usuario cargado")
                    }
                    .addOnFailureListener{
                        Log.i("firebase-firestore","Fallo cargar usuario")
                    }
            }
        }
    }

    fun setearBienvenida(){
        val textViewBienvenida = findViewById<TextView>(R.id.textView)
        val botonLogin = findViewById<Button>(R.id.btn_login)
        val botonLogout = findViewById<Button>(R.id.btn_logout)
        val botonProducto = findViewById<Button>(R.id.btn_ir_producto)
        val botonRestaurante = findViewById<Button>(R.id.btn_it_restaurante)

        if(BAuthUsuario.usuario != null){
            textViewBienvenida.text = "Bienvenido ${BAuthUsuario.usuario?.email}"
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
            botonProducto.visibility = View.VISIBLE
            botonRestaurante.visibility = View.VISIBLE
        }else{
            textViewBienvenida.text = "Ingrese al applicativo"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE
            botonProducto.visibility = View.INVISIBLE
            botonRestaurante.visibility = View.INVISIBLE
        }
    }

    fun solicitarSalirDelAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener{
                BAuthUsuario.usuario = null
                setearBienvenida()
            }
    }

}