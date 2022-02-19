package com.example.a03_recycler_view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts

class GUI_Notification : AppCompatActivity() {

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
        setContentView(R.layout.activity_gui_notification)
    }

    override fun onStart() {
        super.onStart()

        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        val btn_notification_homt = findViewById<ImageButton>(R.id.btn_notification_home)
        btn_notification_homt.setOnClickListener {
            devolverRespuesta()
        }

        val btn_notification_search = findViewById<ImageButton>(R.id.btn_notification_search)
        btn_notification_search.setOnClickListener {
            devolverRespuesta()
        }

        val btn_notification_message = findViewById<ImageButton>(R.id.btn_notification_messages)
        btn_notification_message.setOnClickListener {
            abrirActividadConParametros(GUI_Message::class.java)
        }

    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentAddNewPost = Intent(this, clase)
        intentAddNewPost.putExtra("active-user", active_user)
        resultAddNewPost.launch(intentAddNewPost)
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

}