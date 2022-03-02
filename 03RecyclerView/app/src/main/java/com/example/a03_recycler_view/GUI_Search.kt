package com.example.a03_recycler_view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts

class GUI_Search : AppCompatActivity() {

    var active_user = BPersona("", 0, "", "", "", "", "")

    var resultAddNewPost = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                active_user = data?.getParcelableExtra<BPersona>("active-user")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_search)
    }

    override fun onStart() {
        super.onStart()

        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        val btn_search_home = findViewById<ImageButton>(R.id.btn_search_home)
        btn_search_home.setOnClickListener {
            devolverRespuesta()
        }

        val btn_search_notification = findViewById<ImageButton>(R.id.btn_search_notification)
        btn_search_notification.setOnClickListener {
            abrirActividadConParametros(GUI_Notification::class.java)
        }

        val btn_search_message = findViewById<ImageButton>(R.id.btn_search_messages)
        btn_search_message.setOnClickListener {
            abrirActividadConParametros(GUI_Message::class.java)
        }


    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("active-user",active_user)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentAddNewPost = Intent(this, clase)
        intentAddNewPost.putExtra("active-user", active_user)
        resultAddNewPost.launch(intentAddNewPost)
    }


}