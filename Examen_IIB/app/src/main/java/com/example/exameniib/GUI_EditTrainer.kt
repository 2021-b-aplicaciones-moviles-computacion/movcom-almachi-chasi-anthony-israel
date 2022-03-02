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

class GUI_EditTrainer : AppCompatActivity() {

    var trainer_update = FirestoreTrainer("","","")
    val db = Firebase.firestore
    val trainers = db.collection("PokemonTrainers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_edit_trainer)
    }

    override fun onStart() {
        super.onStart()

        trainer_update = intent.getParcelableExtra<FirestoreTrainer>("PokemonTrainer")!!

        val in_trainer_name = findViewById<EditText>(R.id.in_update_name_trainer)
        in_trainer_name.setText(trainer_update.trainerName.toString())

        val in_trainer_age = findViewById<EditText>(R.id.in_update_age_trainer)
        in_trainer_age.setText(trainer_update.age)

        val btn_confirm_update = findViewById<Button>(R.id.btn_confirm_update_trainer)
        btn_confirm_update.setOnClickListener {
            trainers.document("${trainer_update.trainerId}").update(
                "trainer_age", in_trainer_age.text.toString(),
                "trainer_name" , in_trainer_name.text.toString()
            )
            Toast.makeText(this,"Info. actualizada exitosamente",Toast.LENGTH_SHORT).show()
        }

        val btn_cancel_update = findViewById<Button>(R.id.btn_cancel_update_trainer)
        btn_cancel_update.setOnClickListener {
            val intent = Intent(this, GUI_TrainersList::class.java)
            startActivity(intent)
        }

    }

}