package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        var btn_startApp = findViewById<Button>(R.id.btn_startApp);
        btn_startApp.setOnClickListener {
            val intent = Intent(this, GUI_TrainersList::class.java)
            startActivity(intent)
        }

    }

}