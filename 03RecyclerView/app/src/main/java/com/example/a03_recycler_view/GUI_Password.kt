package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class GUI_Password : AppCompatActivity() {

    var  active_user = BPersona("",0,"","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_password)
    }

    override fun onStart() {
        super.onStart()

        var password = ""
        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        val txtIn_username = findViewById<TextInputEditText>(R.id.txtIn_username_password)
        val txtIn_password = findViewById<TextInputEditText>(R.id.txtIn_pass_password)

        txtIn_username.setText(active_user.username)

        val btn_back_from_Password = findViewById<Button>(R.id.btn_back_from_Password)
        btn_back_from_Password.setOnClickListener {
            val intent = Intent(this,GUI_Login::class.java)
            startActivity(intent)
        }

        val btn_iniciar_sesion = findViewById<Button>(R.id.btn_iniciarSesion_pass)
        btn_iniciar_sesion.setOnClickListener {
            password = txtIn_password.text.toString()
            if (password == active_user.password){
                Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
                abrirGUI_HomeConParametros(GUI_Home::class.java)
            }else{
                Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun abrirGUI_HomeConParametros(
        clase: Class<*>
    ) {
        val intentPasswordToHome = Intent(this, clase)
        intentPasswordToHome.putExtra("active-user", active_user)
        startActivity(intentPasswordToHome)
    }

}