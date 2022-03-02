package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_AddTrainer : AppCompatActivity() {

    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_add_trainer)
    }

    override fun onStart() {
        super.onStart()

        val in_trainer_name = findViewById<EditText>(R.id.in_trainer_name)
        val in_trainer_age = findViewById<EditText>(R.id.in_trainer_age)

        val btn_confirm_add_trainer = findViewById<Button>(R.id.btn_confirm_add_trainer)
        btn_confirm_add_trainer.setOnClickListener {
            var trainer = hashMapOf(
                "trainer_name" to in_trainer_name.text.toString(),
                "trainer_age" to in_trainer_age.text.toString()
            )
            trainers.add(trainer).addOnSuccessListener {
                in_trainer_name.text.clear()
                in_trainer_age.text.clear()
                Toast.makeText(this,"Entrenador registrado exitosamente",Toast.LENGTH_SHORT).show();
                Log.i("Add-Trainer","Success")
            }.addOnFailureListener{
                Log.i("Add-Trainer","Failed")
            }

        }

        val btn_cancell_add_trainer = findViewById<Button>(R.id.btn_cancel_add_trainer)
        btn_cancell_add_trainer.setOnClickListener {
            val intent = Intent(this, GUI_TrainersList::class.java)
            startActivity(intent)
        }

    }

}