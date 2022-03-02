package com.example.a03_recycler_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class GUI_Message : AppCompatActivity() {

    var active_user = BPersona("", 0, "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_message)
    }

    override fun onStart() {
        super.onStart()

        active_user = intent.getParcelableExtra<BPersona>("active-user")!!

        val btn_notification_homt = findViewById<ImageButton>(R.id.btn_message_home)
        btn_notification_homt.setOnClickListener {
            devolverRespuesta()
        }

        val btn_message_search = findViewById<ImageButton>(R.id.btn_message_search)
        btn_message_search.setOnClickListener {
            devolverRespuesta()
        }

        val btn_message_notification = findViewById<ImageButton>(R.id.btn_message_notification)
        btn_message_notification.setOnClickListener {
            devolverRespuesta()
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

}