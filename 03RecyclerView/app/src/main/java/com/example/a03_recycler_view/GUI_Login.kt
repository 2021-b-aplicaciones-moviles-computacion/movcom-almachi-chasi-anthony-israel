package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class GUI_Login : AppCompatActivity() {

    var username = ""
    var active_user = BPersona("",0,"","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_login)
    }

    override fun onStart() {
        super.onStart()
        val btn_back_login = findViewById<Button>(R.id.btn_back_from_Login)
        btn_back_login.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        var txtIn_username = findViewById<TextInputEditText>(R.id.txtIn_username_login)

        val btn_next = findViewById<Button>(R.id.btn_next_login)
        var find_user = 0
        btn_next.setOnClickListener {
            find_user = 0
            username = txtIn_username.text.toString()
            BBaseDeDatosMemoria.arregloPersona.forEach { persona: BPersona ->
                if(username == persona.username || username == persona.email || username == persona.phone){
                    active_user = persona
                    find_user = 1
                    Log.i("test-login","el usuario ${username} si existe")
                    abrirGUI_PassConParametros(GUI_Password::class.java)
                }else if (find_user == 0){
                    Log.i("test-login","el usuario ${username} NO existe ")
                    Toast.makeText(this,"Usuario no encontrado",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun abrirGUI_PassConParametros(
        clase: Class<*>
    ) {
        val intentLoginToPassword = Intent(this, clase)
        intentLoginToPassword.putExtra("active-user", active_user)
        startActivity(intentLoginToPassword)
    }

}